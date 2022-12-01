package com.minikode.api_common.controller

import com.minikode.api_common.config.CloudConfigConst
import com.minikode.api_common.projection.BoardInfo
import com.minikode.api_common.repository.BoardRepositorySupport
import com.minikode.api_common.service.BoardService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/board")
class BoardController(
    private val boardService: BoardService,
    private val boardRepositorySupport: BoardRepositorySupport,
    private val cloudConfigConst: CloudConfigConst,
) {

    private val logger = KotlinLogging.logger { }

    @GetMapping("/")
    suspend fun getBoards(): String {
        logger.debug("cloudConfigConst.debugFlag ${cloudConfigConst.debugFlag}")
        logger.info("cloudConfigConst.debugFlag ${cloudConfigConst.debugFlag}")
        return boardService.get()
    }

    @GetMapping("/temp")
    suspend fun temp(): MutableList<BoardInfo> {
        return boardRepositorySupport.get()
    }


}