package com.minikode.jpa.reactive.repository;

import com.minikode.jpa.entity.MemberEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface MemberReactiveRepository :
    CoroutineCrudRepository<MemberEntity, UUID> {
}