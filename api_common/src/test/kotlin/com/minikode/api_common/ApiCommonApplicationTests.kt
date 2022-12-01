package com.minikode.api_common

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["classpath=application-local.yaml"])
class ApiCommonApplicationTests {

    @Test
    fun contextLoads() {
    }

}
