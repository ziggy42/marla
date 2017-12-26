package com.marla.worker

import mu.KotlinLogging
import redis.clients.jedis.Jedis

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    val client = Jedis("localhost", 6379)
    while (true) {
        val job = client.blpop("marla:waitQueue", "0")

        logger.debug { "New job received: $job" }
    }
}