package com.marla.gatekeeper.service

import com.marla.gatekeeper.config.QueueConfiguration
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool

@Service
class GateKeeperService(
    private val pool: JedisPool,
    private val queueConfiguration: QueueConfiguration
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun isEnabled(): Boolean {
        log.debug("Check if enabled...")

        this.pool.resource?.let {
            val enabled = it.llen(queueConfiguration.name) <= queueConfiguration.maxSize
            log.debug(if (enabled) "Enabled" else "Not Enabled")
            it.close()
            return enabled
        }

        throw RuntimeException("Couldn't obtain pool resource")
    }
}