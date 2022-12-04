package com.minikode.api_common.service

import com.minikode.api_common.aop.FuncLogging
import com.minikode.api_common.repository.BoardRepositorySupport
import com.minikode.jpa.entity.BoardEntity
import com.minikode.jpa.repository.BoardRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val boardRepositorySupport: BoardRepositorySupport,
) {

    private val logger = KotlinLogging.logger { }


    @FuncLogging
    @Transactional
    fun blockingGet(): String {
        val boardEntity0 = BoardEntity(
            title = "0번",
            description = "0번꺼꺼",
        )
        boardRepository.save(boardEntity0)
        val boardEntity1 = BoardEntity(
            title = "1번",
            description = "1번꺼꺼",
        )
        boardRepository.save(boardEntity1)
        val boardIds = mutableListOf<String>()
        boardRepository.findAll()
            .forEach { boardIds.add(it.boardId.toString()) }
        return boardIds.joinToString(",")
    }

}