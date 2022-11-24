package com.minikode.api_common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan("com.minikode.jpa.*")
@EnableJpaRepositories("com.minikode.jpa.repository")
class JpaConfig {
}