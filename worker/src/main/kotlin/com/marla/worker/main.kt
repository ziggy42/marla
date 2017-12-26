package com.marla.worker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import monkey.`object`.Environment
import monkey.ast.Parser
import monkey.evaluator.Evaluator
import monkey.lexer.StringLexer
import mu.KotlinLogging
import redis.clients.jedis.Jedis

private val logger = KotlinLogging.logger {}

data class Job(val clientId: String, val script: String)

fun main(args: Array<String>) {
    val client = Jedis("localhost", 6379)
    val mapper = jacksonObjectMapper()

    generateSequence { client.blpop("marla:waitQueue", "0")[1] }
        .map { mapper.readValue<Job>(it) }
        .forEach(::processNewJob)
}

fun processNewJob(job: Job) {
    logger.debug { "Processing new job: $job" }
    val program = Parser(StringLexer(job.script)).parseProgram()
    val result = Evaluator.eval(program, Environment()).inspect()
    println(result)
}