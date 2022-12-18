package com.minikode.api_common.vo

import com.minikode.jpa.entity.MemberEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUserDetails : UserDetails {

    private var username: String = ""
    private var password: String = ""

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun make(memberEntity: MemberEntity): SecurityUserDetails {
            val securityUserDetails = SecurityUserDetails()
            securityUserDetails.username = memberEntity.accessId
            securityUserDetails.password = memberEntity.password
            return securityUserDetails
        }
    }
}