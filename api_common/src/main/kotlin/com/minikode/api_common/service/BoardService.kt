package com.minikode.api_common.service

import com.minikode.api_common.aop.CoroutineFuncLogging
import com.minikode.jpa.entity.BoardEntity
import com.minikode.jpa.blocking.repository.BoardRepository
import com.minikode.jpa.reactive.repository.BoardReactiveRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BoardService(
    private val boardRepository: BoardRepository,
//    private val boardReactiveRepository: BoardReactiveRepository,
//    private val boardRepositorySupport: BoardRepositorySupport,
) {

    private val logger = KotlinLogging.logger { }


    @CoroutineFuncLogging
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
//            boardReactiveRepository.save(boardEntity0)
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
//            boardReactiveRepository.save(boardEntity1)
        }

        logger.debug { "5" }
        logger.debug { "6" }
//        awaitAll(task0, task1)
        task0.await()
        task1.await()

        logger.debug { "7" }
//        val boardEntities0 = CoroutineScope(Dispatchers.IO).async {
//            delay(1000L)
//            boardRepositorySupport.get()
//        }.await()
//        logger.debug { "8 querydsl 엔티티 개수 : ${boardEntities0.size}" }
        val boardIds = mutableListOf<String>()
        val boardEntities = CoroutineScope(Dispatchers.IO).async {
            delay(1000L)
//            boardReactiveRepository.findAll().asFlow()
        }.await()
//        boardEntities.map { boardIds.add(it.boardId.toString()) }
        logger.debug { "9 jpa 엔티티 개수 : ${boardIds.size}" }
        return boardIds.joinToString(",")
    }

    @Transactional
    fun blockingGet(): String {
        Thread.sleep(1000)
        val boardEntity0 = BoardEntity(
            title = "0번",
            description = "0번꺼꺼",
        )
        Thread.sleep(1000)
        boardRepository.save(boardEntity0)
        val boardEntity1 = BoardEntity(
            title = "1번",
            description = "1번꺼꺼",
        )
        logger.debug { "4" }
        Thread.sleep(1000)
        boardRepository.save(boardEntity1)
        Thread.sleep(1000)
//        val boardEntities0 = boardRepositorySupport.get()
//        logger.debug { "8 querydsl 엔티티 개수 : ${boardEntities0.size}" }
        Thread.sleep(1000)
        val boardIds = mutableListOf<String>()
        boardRepository.findAll()
            .forEach { boardIds.add(it.boardId.toString()) }
        logger.debug { "9 jpa 엔티티 개수 : ${boardIds.size}" }
        return boardIds.joinToString(",")
    }

//    @CoroutineFuncLogging
//    @Transactional
//    suspend fun getReactive(): String {
//
//        val boardEntity0 = BoardEntity(
//            title = "0번",
//            description = "0번꺼꺼",
//        )
//        Thread.sleep(1000)
//        val task0 = CoroutineScope(Dispatchers.IO).async {
//            boardReactiveRepository.save(boardEntity0)
//        }
//        boardRepository.save(boardEntity0)
//        val boardEntity1 = BoardEntity(
//            title = "1번",
//            description = "1번꺼꺼",
//        )
//        logger.debug { "4" }
//        val task1 = CoroutineScope(Dispatchers.IO).async {
//            boardReactiveRepository.save(boardEntity1)
//        }
//
//        task0.await()
//        task1.await()
//
//        val boardEntities = CoroutineScope(Dispatchers.IO).async {
//            boardReactiveRepository.findByTitleLike("")
//        }.await()
//
////        val boardIds = mutableListOf<String>()
//
//        return boardEntities.map { it.boardId.toString() }.joinToString(",")
//
//
//    }

}