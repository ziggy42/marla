package com.marla.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.marla.api.config.RedisConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import redis.clients.jedis.JedisPool

@SpringBootApplication
class MarlaApiApplication {

    @Bean
    fun jedisPool(configuration: RedisConfiguration): JedisPool = JedisPool(configuration.host, configuration.portValue)

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate().apply {
        val messageConverter = MappingJackson2HttpMessageConverter()
        messageConverter.setPrettyPrint(false)
        messageConverter.objectMapper = ObjectMapper()
        messageConverters.add(messageConverter)
    }
}

fun main(args: Array<String>) {
    runApplication<MarlaApiApplication>()
}