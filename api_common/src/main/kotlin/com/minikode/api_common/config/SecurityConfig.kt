package com.minikode.api_common.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.minikode.api_common.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtTokenFilterConfigurer: JwtTokenFilterConfigurer,
) {


    @Bean
    fun providerSecurityFilterChain(http: HttpSecurity, jwtProvider: JwtProvider, CookieUtil: CookieUtil): SecurityFilterChain {

//        http.cors()

        http.csrf().disable()

//        http.exceptionHandling()
//            .authenticationEntryPoint { request, response, authException ->
//                response.status = HttpStatus.UNAUTHORIZED.value()
//                throw Service
//            }

        http.apply(jwtTokenFilterConfigurer)
        return http.build()

    }


}

/*
http
            .cors() //(1)
            .and()
            .csrf() //(2)
            .disable()
            .exceptionHandling() //(3)
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement() //(4)
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests() // (5)
            .antMatchers(RestControllerBase.API_URI_PREFIX + "/auth/**")
            .permitAll()
            .antMatchers(RestControllerBase.API_URI_PREFIX + "/**")
            .authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .formLogin().disable().headers().frameOptions().disable();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
 */