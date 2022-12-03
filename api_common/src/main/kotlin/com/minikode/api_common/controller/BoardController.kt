package com.minikode.api_common.controller

import com.minikode.api_common.config.CloudConfigConst
import com.minikode.api_common.projection.BoardInfo
//import com.minikode.api_common.repository.BoardRepositorySupport
import com.minikode.api_common.service.BoardService
import com.minikode.jpa.entity.BoardEntity
import com.minikode.jpa.reactive.repository.MemberReactiveRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


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
    fun getBoardsBlocking(): String {
        return boardService.blockingGet()
    }

    @GetMapping("/reactive")
    suspend fun getBoardsReactive(): String {
        return boardService.getReactive()
    }

    @GetMapping("/reactive2")
    suspend fun getBoardsReactive2(): ResponseEntity<String> {
        return ResponseEntity.ok(boardService.getReactive2())
    }

    @GetMapping("/reactive3")
    suspend fun getBoardsReactive3(): ResponseEntity<String> {
        return ResponseEntity.ok().body("ddd")
//        return ResponseEntity.ok().body(boardService.getReactive3().)
    }

}