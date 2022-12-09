package com.minikode.common.exception

import com.minikode.common.exception.code.BaseExCode

class ServiceException(
    exCode: BaseExCode,
    message: String? = null,
) : Exception() {

    override val message: String? = message ?: exCode.msg
}