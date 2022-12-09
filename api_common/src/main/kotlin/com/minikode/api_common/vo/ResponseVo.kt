package com.minikode.api_common.vo

class ResponseVo(
    var code: Int = 1,
    var message: String? = "success",
    var data: Any? = null,
)