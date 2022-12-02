package com.minikode.jpa.reactive.repository;

import com.minikode.jpa.entity.BoardEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface BoardReactiveRepository : ReactiveCrudRepository<BoardEntity, UUID> {

    fun findByTitleLike(title: String): MutableList<BoardEntity>


}