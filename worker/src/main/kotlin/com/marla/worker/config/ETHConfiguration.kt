package com.marla.worker.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "eth")
class ETHConfiguration {
    lateinit var address: String
    lateinit var passphrase: String
    lateinit var ipcPath: String
}