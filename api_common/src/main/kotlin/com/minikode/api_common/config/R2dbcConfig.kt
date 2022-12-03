package com.minikode.api_common.config

import io.r2dbc.spi.ConnectionFactory
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager


@Configuration
@EnableR2dbcRepositories("com.minikode.jpa.reactive.repository")
@Import(DataSourceAutoConfiguration::class)
class R2dbcConfig {

    private val logger = KotlinLogging.logger { }

    @Qualifier("R2dbcTransactionManager")
    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager? {
        return R2dbcTransactionManager(connectionFactory)
    }

}