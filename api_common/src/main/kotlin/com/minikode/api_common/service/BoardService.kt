package com.minikode.api_common.service

import com.minikode.api_common.aop.ExeTimer
import com.minikode.api_common.repository.BoardRepositorySupport
import com.minikode.jpa.entity.BoardEntity
import com.minikode.jpa.repository.BoardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.system.measureTimeMillis


@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val boardRepositorySupport: BoardRepositorySupport,
) {

    private val logger = KotlinLogging.logger { }

    @ExeTimer
    @Transactional
    suspend fun get(): String {

        val task0 = CoroutineScope(Dispatchers.IO).async {
            delay(1000L)
            logger.debug { "1" }
            val boardEntity0 = BoardEntity(
                title = "0번",
                description = "0번꺼꺼",
            )
            delay(2000L)
            logger.debug { "2" }
            boardRepository.save(boardEntity0)
        }

        val task1 = CoroutineScope(Dispatchers.IO).async {
            delay(1000L)
            logger.debug { "3" }
            val boardEntity1 = BoardEntity(
                title = "1번",
                description = "1번꺼꺼",
            )
            delay(1000L)
            logger.debug { "4" }
            boardRepository.save(boardEntity1)
        }

        logger.debug { "5" }
        logger.debug { "6" }
//        awaitAll(task0, task1)
        task0.await()
        task1.await()
        logger.debug { "7" }
        val boardEntities0 =
            CoroutineScope(Dispatchers.IO).async { boardRepositorySupport.get() }
                .await()
        logger.debug { "8" }
        val boardEntities =
            CoroutineScope(Dispatchers.IO).async { boardRepository.findAll() }
                .await()
        logger.debug { "9" }
        return boardEntities.map { it.boardId }.joinToString(",")
    }

}