package com.marla.api.service.job

import com.marla.api.model.Job
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool

/**
 * [JobsQueue] implementation that uses a [Redis](https://redis.io/) instance as backend
 */
@Service
class RedisJobsQueue(
    private val pool: JedisPool,
    @Value("\${maria.redis.queue.key}") private val queueKey: String
) : JobsQueue {

    override fun publish(job: Job): Boolean {
        this.pool.resource?.let {
            it.lpush(queueKey, job.toJSON())
            it.close()
            return true
        }

        return false
    }
}