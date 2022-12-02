package com.minikode.api_common.controller

import com.minikode.api_common.config.CloudConfigConst
import com.minikode.api_common.projection.BoardInfo
//import com.minikode.api_common.repository.BoardRepositorySupport
import com.minikode.api_common.service.BoardService
import com.minikode.jpa.entity.BoardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/board")
class BoardController(
    private val boardService: BoardService,
    private val cloudConfigConst: CloudConfigConst,
) {

    private val logger = KotlinLogging.logger { }

    @GetMapping("/")
    suspend fun getBoards(): String {
        return boardService.get()
    }

    @GetMapping("/blocking")
    suspend fun getBoardsBlocking(): String {
        return boardService.blockingGet()
    }



}