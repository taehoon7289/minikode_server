package com.minikode.common.exception

import com.minikode.common.exception.code.BaseExCode

class ServiceRuntimeException(
    exCode: BaseExCode,
    message: String? = null,
) : RuntimeException() {
    override val message: String? = message ?: exCode.msg
}