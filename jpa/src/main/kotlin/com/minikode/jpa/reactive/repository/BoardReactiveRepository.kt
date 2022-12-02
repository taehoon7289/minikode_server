package com.minikode.jpa.reactive.repository;

import com.minikode.jpa.entity.BoardEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface BoardReactiveRepository : CoroutineCrudRepository<BoardEntity, UUID> {

}