package com.marla.worker.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.marla.worker.config.QueueConfiguration
import com.marla.worker.model.Job
import com.marla.worker.model.Result
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool

@Service
class Queue(
    private val pool: JedisPool,
    private val queueConfiguration: QueueConfiguration,
    private val mapper: ObjectMapper
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getJob(): Job {
        pool.resource?.let {
            val json = it.blpop(queueConfiguration.waitQueue, "0")[1]
            it.close()

            log.debug("New value popped from queue: $json")
            return mapper.readValue(json)
        }

        throw RuntimeException("Something went horribly wrong reading a new job from the queue")
    }

    fun publishResult(result: Result) {
        pool.resource?.let {
            val json = mapper.writeValueAsString(result)

            log.debug("Pushing value to queue: $json")

            it.lpush(queueConfiguration.resultsQueue, json)
            it.close()
        }
    }
}