package com.minikode.api_common.aop

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
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
class FuncLoggingAspect {

    private val logger = KotlinLogging.logger { }

    @Pointcut("execution(@FuncLogging * *.*(..))")
    fun log() {}

    @Around("log()")
    fun executeLog(joinPoint: ProceedingJoinPoint) {

        val method = (joinPoint.signature as MethodSignature).method
        val stopWatch = StopWatch()
        stopWatch.start()
        logger.debug { "start!!" }
        joinPoint.proceed()
        logger.debug { "stop!!" }
        stopWatch.stop()
        val result = stopWatch.totalTimeMillis
        logger.debug {
            """
                실행메소드 : ${(joinPoint.signature as MethodSignature).method.name}
                실행시간 : $result   
            """.trimIndent()

        }
    }

    private fun temp(joinPoint: ProceedingJoinPoint): Any? = runBlocking {
        return@runBlocking CoroutineScope(Dispatchers.IO).async {
            joinPoint.proceed()
        }.await()
    }

}