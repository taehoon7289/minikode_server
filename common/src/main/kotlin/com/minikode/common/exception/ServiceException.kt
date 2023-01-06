package com.minikode.common.exception

import com.minikode.common.exception.code.BaseExCode

class ServiceException(
    exCode: BaseExCode,
    message: String? = null,
) : Exception() {
    val code: Int = exCode.code
    override val message: String? = message ?: exCode.msg
}