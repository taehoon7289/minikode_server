package com.minikode.api_common.jwt

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val logger =
        LoggerFactory.getLogger(JwtAuthenticationEntryPoint::class.java)

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.debug(
            "Responding with unauthorized error. Message - {}",
            authException.message
        )
        response.sendError(
            HttpStatus.UNAUTHORIZED.value(),
            ""
        );
    }
}