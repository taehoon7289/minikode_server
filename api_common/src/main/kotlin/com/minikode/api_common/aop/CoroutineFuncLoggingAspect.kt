package com.minikode.api_common.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn

val ProceedingJoinPoint.coroutineContinuation: Continuation<Any?>
    get() = this.args.last() as Continuation<Any?>

val ProceedingJoinPoint.coroutineArgs: Array<Any?>
    get() = this.args.sliceArray(0 until this.args.size - 1)

suspend fun ProceedingJoinPoint.proceedCoroutine(
    args: Array<Any?> = this.coroutineArgs
): Any? = suspendCoroutineUninterceptedOrReturn { continuation ->
    args.forEach {
        println("pCarg: $it")
    }
    this.proceed(args + continuation)
}

fun ProceedingJoinPoint.runCoroutine(
    block: suspend () -> Any?
): Any? = block.startCoroutineUninterceptedOrReturn(this.coroutineContinuation)

@Component
@Aspect
class CoroutineFuncLoggingAspect {
    private val logger = KotlinLogging.logger { }

    @Pointcut("execution(@CoroutineFuncLogging * *.*(..))")
    private fun log() {
    }

    @Around(
        """
        log() &&
        args(.., kotlin.coroutines.Continuation)
        """
    )
    fun executeLog(joinPoint: ProceedingJoinPoint): Any? {
        logger.debug { "executeLog" }
        return joinPoint.runCoroutine {

            val method = (joinPoint.signature as MethodSignature).method
            val stopWatch = StopWatch()
            stopWatch.start()
            logger.debug { "start!!" }
            val result = joinPoint.proceedCoroutine()
            logger.debug { "stop!!" }
            stopWatch.stop()
            val time = stopWatch.totalTimeMillis
            logger.debug {
                """
                실행메소드 : ${(joinPoint.signature as MethodSignature).method.name}
                실행시간 : $time   
            """.trimIndent()

            }
            return@runCoroutine result
        }
    }
}
