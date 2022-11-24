package com.minikode.api_common

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ApiCommonApplication

private val logger = KotlinLogging.logger { }

fun main(args: Array<String>) {
//    logger.debug("???$$$$$")
    logger.debug("hahahah1111")
//    logger.debug {
//        "가나다라마바사  아아아ㅏ  ㄴㅇㄴㅇㄹ"
//    }
    runApplication<ApiCommonApplication>(*args)
}
