package com.marla.worker.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "redis")
class RedisConfiguration {
    var portValue: Int = 0
    lateinit var host: String
}