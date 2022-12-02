package com.minikode.jpa.reactive.repository;

import com.minikode.jpa.entity.MemberEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface MemberReactiveRepository :
    ReactiveCrudRepository<MemberEntity, UUID> {
}