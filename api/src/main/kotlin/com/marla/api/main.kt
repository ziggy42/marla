package com.marla.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class MarlaApiApplication

fun main(args: Array<String>) {
    runApplication<MarlaApiApplication>()
}

@RestController
class HelloWorldController {

    @GetMapping("/")
    fun hello(): String = "Hello, World"
}