package com.minikode.jpa.blocking.repository;

import com.minikode.jpa.entity.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface BoardRepository : JpaRepository<BoardEntity, UUID> {

}