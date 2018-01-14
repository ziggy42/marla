package com.marla.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.marla.websocket.service.ResultsDispatcher
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import redis.clients.jedis.JedisPool

@SpringBootApplication
class MarlaWebSocketApplication {

    @Bean
    fun jedisPool(
        @Value("\${marla.redis.host}") host: String,
        @Value("\${marla.redis.port}") port: Int): JedisPool = JedisPool(host, port)

    @Bean
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()
}

fun main(args: Array<String>) {
    val context = runApplication<MarlaWebSocketApplication>()
    val dispatcher = context.getBean(ResultsDispatcher::class.java)
    dispatcher.start()
}