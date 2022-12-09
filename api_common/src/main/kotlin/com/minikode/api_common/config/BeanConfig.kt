package com.minikode.api_common.config

import com.minikode.common.aop.FuncLoggingAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class BeanConfig {

    @Bean
    fun registerFuncLoggingAspect() = FuncLoggingAspect()

    @Bean
    fun registerBCryptPasswordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun registerAuthenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

}