package com.minikode.api_common.service.impl

import com.minikode.common.aop.FuncLogging
import com.minikode.api_common.repository.BoardRepositorySupport
import com.minikode.api_common.service.BoardService
import com.minikode.jpa.entity.BoardEntity
import com.minikode.jpa.repository.BoardRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service("boardService")
class BoardServiceImpl(
    private val boardRepository: BoardRepository,
    private val boardRepositorySupport: BoardRepositorySupport,
) : BoardService {

    private val logger = LoggerFactory.getLogger(BoardServiceImpl::class.java)


    @FuncLogging
    @Transactional
    override fun get(): String {
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
//        boardRepository.findAll()
//            .forEach {
//                boardIds.add(it.boardId.toString())
//            }
        boardRepositorySupport.get().forEach {
            boardIds.add(it.boardId.toString())
        }
        return boardIds.joinToString(",")
    }

}