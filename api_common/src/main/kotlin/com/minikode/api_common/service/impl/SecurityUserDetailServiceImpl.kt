package com.minikode.api_common.service.impl

import com.minikode.api_common.vo.SecurityUserDetails
import com.minikode.common.exception.ServiceRuntimeException
import com.minikode.common.exception.code.CommonExCode
import com.minikode.jpa.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailServiceImpl(
    private val memberRepository: MemberRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val memberEntity =
            memberRepository.findByAccessId(username).orElseThrow {
                throw ServiceRuntimeException(exCode = CommonExCode.NO_EXIST_DATA)
            }
        return SecurityUserDetails.make(memberEntity)
    }
}