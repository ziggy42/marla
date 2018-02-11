package com.marla.worker.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "queue")
class QueueConfiguration {
    lateinit var waitQueue: String
    lateinit var resultsQueue: String
}