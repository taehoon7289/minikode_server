package com.minikode.api_common.service.impl

import com.minikode.api_common.vo.SecurityUserDetails
import com.minikode.common.exception.ServiceRuntimeException
import com.minikode.common.exception.code.CommonExCode
import com.minikode.jpa.entity.MemberRoleEntity
import com.minikode.jpa.repository.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Function
import java.util.stream.Stream
import kotlin.streams.toList


@Service
class SecurityUserDetailServiceImpl(
    private val memberRepository: MemberRepository,
) : UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val memberEntity =
            memberRepository.findByAccessId(username).orElseThrow {
                throw ServiceRuntimeException(exCode = CommonExCode.NO_EXIST_DATA)
            }
        return SecurityUserDetails.make(memberEntity)
    }


}