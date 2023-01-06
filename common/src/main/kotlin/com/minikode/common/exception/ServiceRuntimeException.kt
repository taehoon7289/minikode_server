package com.minikode.common.exception

import com.minikode.common.exception.code.BaseExCode

class ServiceRuntimeException(
    val exCode: BaseExCode,
    message: String? = null,
) : RuntimeException() {

    override val message: String? = message ?: exCode.msg
}