package com.minikode.common.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

@Aspect
class FuncLoggingAspect {

    private val logger = LoggerFactory.getLogger(FuncLoggingAspect::class.java)

    @Pointcut("execution(@FuncLogging * *.*(..))")
    fun log() {
    }

    @Around("log()")
    fun executeLog(joinPoint: ProceedingJoinPoint): Any? {

        val method = (joinPoint.signature as MethodSignature).method
        val stopWatch = StopWatch()
        stopWatch.start()
        val proceed = joinPoint.proceed()
        stopWatch.stop()
        val result = stopWatch.totalTimeMillis
        logger.debug(
            "\n" + """
                실행클래스 : ${method.declaringClass.name}
                실행메소드 : ${method.name}
                리턴값: $proceed
                실행시간 : $result ms   
            """.trimIndent()
        )
        return proceed
    }


}