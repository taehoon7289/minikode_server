package com.minikode.jpa.repository;

import com.minikode.jpa.entity.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BoardRepository : JpaRepository<BoardEntity, UUID> {
}