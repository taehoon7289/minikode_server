package com.minikode.api_common.config

import com.minikode.api_common.jwt.JwtTokenProvider
import com.minikode.common.exception.code.BoardExCode
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenFilter(
    private val jwtTokenProvider: JwtTokenProvider,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = jwtTokenProvider.resolveToken(request)
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                val auth: Authentication =
                    jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (ex: RuntimeException) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext()
            response.sendError(
                HttpStatus.BAD_REQUEST.value(), ""
            )
            return
        }

        filterChain.doFilter(request, response)
    }
}