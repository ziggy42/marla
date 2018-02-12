package com.marla.worker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.marla.worker.config.RedisConfiguration
import com.marla.worker.service.JobProcessor
import com.marla.worker.service.Queue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import redis.clients.jedis.JedisPool

@SpringBootApplication
class MarlaWorkerApplication {

    @Bean
    fun jedisPool(configuration: RedisConfiguration) = JedisPool(configuration.host, configuration.portValue)

    @Bean
    fun mapper() = jacksonObjectMapper()
}

fun main(args: Array<String>) {
    val context = runApplication<MarlaWorkerApplication>()

    val queue = context.getBean(Queue::class.java)
    val jobProcessor = context.getBean(JobProcessor::class.java)

    generateSequence { queue.getJob() }.forEach { jobProcessor.processNewJob(it) }
}