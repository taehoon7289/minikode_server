package com.minikode.api_common.config

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories("com.minikode.jpa.reactive.repository")
@Import(DataSourceAutoConfiguration::class)
class R2dbcConfig {

    private val logger = KotlinLogging.logger { }

}