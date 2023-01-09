package com.minikode.api_common.controller

import com.minikode.api_common.config.CloudConfigConst
import com.minikode.api_common.service.BoardService
import com.minikode.api_common.vo.ResponseMapper
import com.minikode.api_common.vo.ResponseVo
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/common/v1/board")
class BoardController(
    private val boardService: BoardService,
    private val cloudConfigConst: CloudConfigConst,
) {

    private val logger = LoggerFactory.getLogger(BoardController::class.java)

    @GetMapping(value = ["", "/"])
    fun get(): ResponseEntity<ResponseVo> {
//        return ResponseEntity.ok(ResponseVo.ok(data = boardService.get()))
        return ResponseMapper.ok(boardService.get())
    }

}