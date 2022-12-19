package com.minikode.api_common.config

import com.minikode.api_common.jwt.JwtAuthenticationEntryPoint
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
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
) {


    @Bean
    fun providerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
        http.httpBasic().disable()
        http.formLogin().disable().headers().frameOptions().disable()
        http.exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .antMatchers("/api/v1/token/**")
            .permitAll()
            .antMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
            .antMatchers("/api/v1/board/**").hasAuthority("USER")
            .and()
            .addFilterBefore(
                JwtTokenFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java,
            )
        return http.build()

    }

//    @Bean
//    fun providerWebSecurityCustomizer(): WebSecurityCustomizer {
//            return web.ignoring()
//                .antMatchers(
//                    "/images/**",
//                    "/js/**",
//                    "/css/**",
//                );
//        };
//    }


}