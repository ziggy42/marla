package com.marla.api

import com.marla.api.config.RedisConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import redis.clients.jedis.JedisPool

@SpringBootApplication
class MarlaApiApplication {

    @Bean
    fun jedisPool(configuration: RedisConfiguration): JedisPool = JedisPool(configuration.host, configuration.port)
}

fun main(args: Array<String>) {
    runApplication<MarlaApiApplication>()
}