package com.marla.gatekeeper

import com.marla.gatekeeper.config.RedisConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import redis.clients.jedis.JedisPool

@SpringBootApplication
class MarlaApiApplication {

    @Bean
    fun jedisPool(configuration: RedisConfiguration): JedisPool = JedisPool(configuration.host, configuration.portValue)
}

fun main(args: Array<String>) {
    runApplication<MarlaApiApplication>()
}