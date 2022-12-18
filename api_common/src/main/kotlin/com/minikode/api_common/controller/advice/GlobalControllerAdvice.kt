package com.minikode.api_common.controller.advice

import com.minikode.common.exception.ServiceException
import com.minikode.common.exception.ServiceRuntimeException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun exception(): String {
        return "exception"
    }

    @ExceptionHandler(ServiceRuntimeException::class)
    fun serviceRuntimeException(): String {
        return "serviceRuntimeException"
    }

    @ExceptionHandler(ServiceException::class)
    fun serviceException(): String {
        return "serviceException"
    }
}