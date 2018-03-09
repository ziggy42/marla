package com.marla.worker.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ssh")
class SSHConfiguration {
    lateinit var username: String
    lateinit var password: String
    lateinit var host: String
}