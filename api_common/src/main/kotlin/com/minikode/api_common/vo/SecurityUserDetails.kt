package com.minikode.api_common.vo

import com.minikode.jpa.entity.MemberEntity
import com.minikode.jpa.entity.MemberRoleEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class SecurityUserDetails : UserDetails {

    private var username: String = ""
    private var password: String = ""
    private var authorities: MutableList<SimpleGrantedAuthority> = ArrayList()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return this.authorities
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
            securityUserDetails.authorities = memberEntity.roles.map {
                SimpleGrantedAuthority(
                    it.role.toString()
                )
            }.toMutableList()
            return securityUserDetails
        }

    }
}