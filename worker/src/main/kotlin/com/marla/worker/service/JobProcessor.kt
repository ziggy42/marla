package com.marla.worker.service

import com.marla.worker.model.Job
import com.marla.worker.model.Result
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JobProcessor(private val queue: Queue, private val ethService: ETHService) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun processNewJob(job: Job) {
        log.debug("Evaluating new job: $job")

        val result = ethService.executeTransaction(job.source, job.destination, job.amount)

        log.debug("Publishing result: $result")

        queue.publishResult(Result(job.clientId, result))
    }
}