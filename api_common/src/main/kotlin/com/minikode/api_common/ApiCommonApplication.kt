package com.minikode.api_common

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiCommonApplication

private val logger = LoggerFactory.getLogger(ApiCommonApplication::class.java)

fun main(args: Array<String>) {
    logger.debug("ggg")
    runApplication<ApiCommonApplication>(*args)
}
