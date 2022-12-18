package com.minikode.jpa.repository;

import com.minikode.jpa.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<MemberEntity, UUID> {


    fun findByAccessId(accessId: String): Optional<MemberEntity>


}