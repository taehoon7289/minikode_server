package com.minikode.common.exception.code

enum class CommonExCode(
    override val code: Int,
    override val msg: String?
) : BaseExCode {

    CALL_MANAGER(-9999, "관리자에게 문의하세요."),
    NO_EXIST_DATA(-10000, "데이터가 존재하지 않습니다."),
    INVALID_TOKEN(-10001, "유효하지 않은 토큰입니다."),
}