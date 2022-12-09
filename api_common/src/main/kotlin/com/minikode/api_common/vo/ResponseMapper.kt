package com.minikode.api_common.vo

import com.minikode.common.exception.code.BaseExCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseMapper {
    companion object {
        fun ok(data: Any? = null): ResponseEntity<ResponseVo> {
            return ResponseEntity.ok(ResponseVo(data = data))
        }

        fun of(
            code: Int, message: String, data: Any? = null
        ): ResponseEntity<ResponseVo> {
            return ResponseEntity.ok(
                ResponseVo(
                    code = code, message = message, data = data
                )
            )
        }

        fun of(
            exCode: BaseExCode, data: Any? = null
        ): ResponseEntity<ResponseVo> {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseVo(
                    code = exCode.code, message = exCode.msg, data = data
                )
            )
        }
    }

}