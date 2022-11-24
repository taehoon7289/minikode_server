package com.minikode.jpa.repository;

import com.minikode.jpa.entity.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface BoardEntityRepository : JpaRepository<BoardEntity, UUID> {
}