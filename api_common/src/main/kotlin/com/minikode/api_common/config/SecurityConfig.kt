package com.minikode.api_common.config

import com.minikode.api_common.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
) {


    @Bean
    fun providerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http.httpBasic().disable()
        http.csrf().disable()
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/*")
            .permitAll()
            .and()
            .addFilterBefore(
                JwtTokenFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java,
            )
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