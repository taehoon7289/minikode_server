package com.minikode.api_common.vo

class ResponseVo(
    var code: Int = 1,
    var message: String? = "정상",
    var data: Any? = null,
)