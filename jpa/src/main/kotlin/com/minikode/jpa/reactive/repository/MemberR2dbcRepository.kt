package com.minikode.jpa.reactive.repository;

import com.minikode.jpa.entity.MemberEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface MemberR2dbcRepository : R2dbcRepository<MemberEntity, String> {
}