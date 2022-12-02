package com.minikode.reactive.repository;

import com.minikode.jpa.entity.BoardEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface BoardReactiveRepository : ReactiveCrudRepository<BoardEntity, UUID> {




}