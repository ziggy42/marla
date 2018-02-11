package com.marla.api.service.job

import com.marla.api.config.QueueConfiguration
import com.marla.api.model.Job
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool

/**
 * [JobsQueue] implementation that uses a [Redis](https://redis.io/) instance as backend
 */
@Service
class RedisJobsQueue(
    private val pool: JedisPool,
    private val queueConfiguration: QueueConfiguration
) : JobsQueue {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun publish(job: Job): Boolean {
        log.debug("Publishing new job for ${job.clientId}")

        this.pool.resource?.let {
            it.lpush(queueConfiguration.name, job.toJSON())
            it.close()

            log.debug("Job published successfully for ${job.clientId}")

            return true
        }

        log.debug("Something went horribly, horribly wrong for ${job.clientId}")

        return false
    }
}