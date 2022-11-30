package com.minikode.api_common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Component

@RefreshScope
@Component
class CloudConfigConst(
    @Value("\${const.profile}") var profile: String,
    @Value("\${const.log}") var log: String,
    @Value("\${const.init-command}") var initCommand: String,
    @Value("\${const.test-flag}") var testFlag: Boolean,

    )