package com.minikode.api_common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Component

@RefreshScope
@Component
@ConfigurationProperties(prefix = "const")
class CloudConfigConst {
    var debugFlag: Boolean = false
}