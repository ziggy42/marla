package com.marla.worker.service

import com.marla.worker.model.Job
import com.marla.worker.model.Result
import monkey.`object`.Environment
import monkey.ast.Parser
import monkey.ast.Program
import monkey.evaluator.Evaluator
import monkey.lexer.StringLexer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JobProcessor(private val queue: Queue) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun processNewJob(job: Job) {
        log.debug("Evaluating new job: $job")

        val result = evalProgram(job)

        log.debug("Publishing result: $result")

        queue.publishResult(result)
    }

    private fun evalProgram(job: Job): Result =
        Result(job.clientId, Evaluator.eval(extractProgram(job), Environment()).inspect())

    private fun extractProgram(job: Job): Program = Parser(StringLexer(job.script)).parseProgram()
}