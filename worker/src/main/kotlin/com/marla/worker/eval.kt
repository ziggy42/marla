package com.marla.worker

import com.marla.worker.model.Job
import com.marla.worker.model.Result
import monkey.`object`.Environment
import monkey.ast.Parser
import monkey.ast.Program
import monkey.evaluator.Evaluator
import monkey.lexer.StringLexer
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun processNewJob(job: Job) {
    logger.debug { "Evaluating new job: $job" }

    val result = evalProgram(job)

    logger.debug { "Publishing result: $result" }

    publishResult(result)
}

private fun evalProgram(job: Job): Result =
    Result(job.clientId, Evaluator.eval(extractProgram(job), Environment()).inspect())

private fun extractProgram(job: Job): Program = Parser(StringLexer(job.script)).parseProgram()
