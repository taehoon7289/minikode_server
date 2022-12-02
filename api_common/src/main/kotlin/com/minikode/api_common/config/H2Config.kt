package com.minikode.api_common.config

import mu.KotlinLogging
import org.h2.tools.Server
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@Configuration
@Profile(value = ["local"])
class H2Config {

    private val logger = KotlinLogging.logger { }
    private lateinit var webServer: Server

    @EventListener(classes = [ContextRefreshedEvent::class])
    fun start() {
        this.webServer =
            Server.createWebServer("-webPort", "8082", "-tcpAllowOthers")
                .start()
        logger.debug { "h2 server start!!" }
    }

    @EventListener(classes = [ContextClosedEvent::class])
    fun close() {

        this.webServer.stop()
        logger.debug { "h2 server stop!!" }
    }
}