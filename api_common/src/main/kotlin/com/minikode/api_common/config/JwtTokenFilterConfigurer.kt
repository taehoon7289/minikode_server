package com.minikode.api_common.config

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class JwtTokenFilterConfigurer(
    private val jwtTokenFilter: JwtTokenFilter,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {


    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(
            jwtTokenFilter,
            UsernamePasswordAuthenticationFilter::class.java
        )
    }
}