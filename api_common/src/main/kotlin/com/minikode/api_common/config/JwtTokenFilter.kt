package com.minikode.api_common.config

import com.minikode.api_common.jwt.JwtTokenProvider
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(
    private val jwtTokenProvider: JwtTokenProvider,
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(JwtTokenFilter::class.java)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolveToken(request)
        log.debug("token :: {}", token)
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                val authentication: Authentication =
                    jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication =
                    authentication
            }
        } catch (ex: RuntimeException) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext()
//            response.sendError(
//                HttpStatus.BAD_REQUEST.value(), ""
//            )
//            return
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken: String? = req.getHeader("Authorization")
        return bearerToken?.let {
            return@let if (it.startsWith("Bearer ")) {
                it.substring(7)
            } else null
        }

    }

}