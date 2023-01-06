package com.minikode.api_common.controller.advice

import com.minikode.api_common.vo.ResponseVo
import com.minikode.common.exception.ServiceException
import com.minikode.common.exception.ServiceRuntimeException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun exception(ex: Exception): ResponseEntity<ResponseVo> {
        return ResponseEntity.ok(ResponseVo(
            code = CommonExCode.CALL_MANAGER.code,
            message = CommonExCode.CALL_MANAGER.msg,
        ))
    }

    @ExceptionHandler(ServiceRuntimeException::class)
    fun serviceRuntimeException(ex: ServiceRuntimeException): ResponseEntity<ResponseVo> {
        return ResponseEntity.ok(ResponseVo(
            code = ex.code,
            message = ex.message,
        ))
    }

    @ExceptionHandler(ServiceException::class)
    fun serviceException(ex: ServiceException): ResponseEntity<ResponseVo> {
        return ResponseEntity.ok(ResponseVo(
            code = ex.code,
            message = ex.message,
        ))
    }
}