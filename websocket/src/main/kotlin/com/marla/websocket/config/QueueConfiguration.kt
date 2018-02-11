package com.marla.websocket.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "queue")
class QueueConfiguration {
    lateinit var name: String
}