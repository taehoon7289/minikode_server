package com.minikode.common.code

enum class AdminRole(
    var label: String
) {
    ROLE_ROOT("최상위"),
    ROLE_INNER("내부"),
    ROLE_OUTER("외부"),

}