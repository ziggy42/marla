package com.marla.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "gatekeeper")
class GatekeeperConfiguration {
    lateinit var endpoint: String
}