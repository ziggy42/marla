package com.marla.api.service.job

import com.marla.api.model.Job
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool

/**
 * [JobsQueue] implementation that uses a [Redis](https://redis.io/) instance as backend
 */
@Service
class RedisJobsQueue(
    private val pool: JedisPool,
    @Value("\${marla.redis.queue.key}") private val queueKey: String
) : JobsQueue {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun publish(job: Job): Boolean {
        log.debug("Publishing new job for ${job.clientId}")

        this.pool.resource?.let {
            it.lpush(queueKey, job.toJSON())
            it.close()

            log.debug("Job published successfully for ${job.clientId}")

            return true
        }

        log.debug("Something went horribly, horribly wrong for ${job.clientId}")

        return false
    }
}