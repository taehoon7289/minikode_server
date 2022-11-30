package com.minikode.api_common.controller

import com.minikode.api_common.config.CloudConfigConst
import com.minikode.api_common.service.BoardService
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
    fun getBoards(): String {
//        boardService.get()

//        logger.debug { cloudConfigConst.profile }
        logger.debug { cloudConfigConst.log }
        logger.debug { cloudConfigConst.initCommand }
        logger.debug { cloudConfigConst.testFlag }

        logger.debug("cloudConfigConst.log ${cloudConfigConst.log}")
        logger.info("cloudConfigConst.log ${cloudConfigConst.log}")
        logger.info("cloudConfigConst.initCommand ${cloudConfigConst.initCommand}")

        logger.debug(boardService.get())
        return "boards"
    }

}