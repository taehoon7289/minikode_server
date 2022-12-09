package com.minikode.api_common.jwt

import com.minikode.common.exception.ServiceRuntimeException
import com.minikode.common.exception.code.CommonExCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenProvider(
    private val userDetailsService: UserDetailsService,
) : AuthenticationProvider {

    private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    private val expireTime: Long = 10000
    private val secretKey: String = "minikode"

    fun generateToken(authentication: Authentication): String? {
        logger.debug("authentication.name : ${authentication.name}")
        val claims = Jwts.claims().setSubject(authentication.name)
        val now = Date()
        logger.debug("now : $now")
        val expiresIn = Date(now.time + expireTime)
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
            .setExpiration(expiresIn)
            .signWith(SignatureAlgorithm.HS256, secretKey).compact()
    }

    fun getAuthentication(token: String): Authentication {
        val claims: Claims =
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
        val userName = claims.subject
        val userDetails: UserDetails =
            userDetailsService.loadUserByUsername(userName)
        return UsernamePasswordAuthenticationToken(
            userDetails, "", userDetails.authorities
        )
    }



    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            throw ServiceRuntimeException(
                exCode = CommonExCode.INVALID_TOKEN,
            )
        } catch (e: IllegalArgumentException) {
            throw ServiceRuntimeException(
                exCode = CommonExCode.INVALID_TOKEN,
            )
        }
    }

    override fun authenticate(authentication: Authentication?): Authentication {
        val userDetails: AccountDetails =
            accountDetailsService.loadUserByUsername(
                authentication!!.principal as String
            ) as AccountDetails


        return UsernamePasswordAuthenticationToken(
            userDetails.getEmail(),
            userDetails.getPassword(),
            userDetails.getAuthorities()
        )
    }

    override fun supports(authentication: Class<*>?): Boolean {
        TODO("Not yet implemented")
    }

}