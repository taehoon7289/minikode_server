package com.minikode.api_common.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.core.KotlinDetector
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class ExecutionTimer {

    private val logger = KotlinLogging.logger { }

    @Pointcut("@annotation(com.minikode.api_common.aop.ExeTimer)")
    fun timer() {
    }

    @Around("timer()")
    fun AssumeExecutionTime(joinPoint: ProceedingJoinPoint) {

        val method = (joinPoint.signature as MethodSignature).method


        val stopWatch = StopWatch()
        stopWatch.start()
        if (KotlinDetector.isSuspendingFunction(method)) {
//            @Suppress("UNCHECKED_CAST")
//            val continuation = joinPoint.args.last() as Continuation<Any?>
//
//            MDC.put("traceId", findOrGenerateTraceId())
//
//            val newContext = continuation.context.plus(MDCContext())
//            val newContinuation = Continuation<Any?>(newContext) { continuation.resumeWith(it) }
//
            val newArgs = joinPoint.args.dropLast(1)
            joinPoint.proceed(newArgs.toTypedArray())
        } else {
            joinPoint.proceed()
        }
        stopWatch.stop()
        val result = stopWatch.totalTimeMillis
        logger.debug {
            """
                실행메소드 : ${(joinPoint.signature as MethodSignature).method.name}
                실행시간 : $result   
            """.trimIndent()

        }
    }

}