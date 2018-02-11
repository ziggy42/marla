package com.marla.websocket.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.marla.websocket.config.QueueConfiguration
import com.marla.websocket.model.Result
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool

@Service
class ResultListenerService(
    private val pool: JedisPool,
    private val mapper: ObjectMapper,
    private val queueConfiguration: QueueConfiguration
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getResult(): Result {
        pool.resource?.let {
            val json = it.blpop(queueConfiguration.name, "0")?.get(1)
            it.close()

            log.debug("New value popped from queue: $json")

            return mapper.readValue(json!!)
        }

        throw RuntimeException("Couldn't obtain pool resource")
    }
}