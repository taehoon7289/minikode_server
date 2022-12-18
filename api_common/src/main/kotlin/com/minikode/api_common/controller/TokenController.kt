package com.minikode.api_common.controller

import com.minikode.api_common.jwt.JwtTokenProvider
import com.minikode.api_common.vo.LoginForm
import com.minikode.api_common.vo.ResponseMapper
import com.minikode.api_common.vo.ResponseVo
import com.minikode.jpa.entity.MemberEntity
import com.minikode.jpa.repository.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/token")
class TokenController(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService,
    private val memberRepository: MemberRepository,
) {

    private val logger = LoggerFactory.getLogger(TokenController::class.java)

    @PostMapping(value = ["", "/"])
    fun createToken(@RequestBody loginForm: LoginForm): ResponseEntity<ResponseVo> {
        val userDetails =
            userDetailsService.loadUserByUsername(loginForm.accessId)
        val authentication = UsernamePasswordAuthenticationToken(
            userDetails, "", userDetails.authorities
        )
        return ResponseMapper.ok(jwtTokenProvider.generateToken(authentication))
    }

    @PostMapping(value = ["/create"])
    fun saveMember(): ResponseEntity<ResponseVo> {
        val memberEntity = MemberEntity(
            accessId = "member1",
            password = "qwer1234",
            name = "회원1",
            email = "taehoon7289@gmail.com",
        )
        return ResponseMapper.ok(memberRepository.save(memberEntity))
    }

}