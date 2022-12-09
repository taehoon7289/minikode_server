package com.minikode.api_common.controller

import com.minikode.api_common.service.BoardService
import com.minikode.jpa.entity.BoardEntity
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc


@WebMvcTest
@AutoConfigureMockMvc
//@JsonTest // ObjectMapper와 @JsonComponent 빈을 포함한 Jackson의 테스트를 위한 모듈들을 자동으로 설정
class BoardControllerTest(
    @MockBean
    private val boardService: BoardService,
    private val mvc: MockMvc,
    private val json: JacksonTester<BoardEntity>
) {


//    @Test
    fun get() {

//        given(boardService.get()).willReturn()

    }
}