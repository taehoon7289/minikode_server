package com.minikode.api_common.service

import com.minikode.jpa.repository.BoardEntityRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BoardService(
    private val boardEntityRepository: BoardEntityRepository,
) {

    private val logger = KotlinLogging.logger { }

    @Transactional
    fun get(): String {
        logger.debug { "get" }
        val boardEntities = boardEntityRepository.findAll()
        return boardEntities.map { it.uuid }.joinToString(",")
    }

}