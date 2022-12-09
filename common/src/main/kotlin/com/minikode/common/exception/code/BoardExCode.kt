package com.minikode.common.exception.code

enum class BoardExCode(
    override val code: Int,
    override val msg: String?
) : BaseExCode {

    INVALID_TITLE(-100, "유효하지 않은 제목입니다.")

}