package com.marla.worker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.marla.worker.model.Job
import com.marla.worker.model.Result
import mu.KotlinLogging
import redis.clients.jedis.Jedis

private val logger = KotlinLogging.logger {}
private val queue = Jedis(config[redis.host], config[redis.open_port])
private val mapper = jacksonObjectMapper()

private var JOBS_QUEUE_KEY = config[redis.jobsQueue]
private var RESULTS_QUEUE_KEY = config[redis.resultsQueue]

fun getJob(): Job {
    val json = queue.blpop(JOBS_QUEUE_KEY, "0")[1]

    logger.debug { "New value popped from queue: $json" }

    return mapper.readValue(json)
}

fun publishResult(result: Result) {
    val json = mapper.writeValueAsString(result)

    logger.debug { "Pushing value to queue: $json" }

    queue.lpush(RESULTS_QUEUE_KEY, json)
}