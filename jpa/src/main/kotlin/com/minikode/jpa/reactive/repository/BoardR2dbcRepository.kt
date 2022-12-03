package com.minikode.jpa.reactive.repository;

import com.minikode.jpa.entity.BoardEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface BoardR2dbcRepository : R2dbcRepository<BoardEntity, Long> {
}