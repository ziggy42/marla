package com.marla.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import redis.clients.jedis.JedisPool

@SpringBootApplication
class MarlaApiApplication {

    @Bean
    fun jedisPool(
            @Value("\${marla.redis.host}") host: String,
            @Value("\${marla.redis.port}") port: Int
    ): JedisPool = JedisPool(host, port)
}

fun main(args: Array<String>) {
    runApplication<MarlaApiApplication>()
}