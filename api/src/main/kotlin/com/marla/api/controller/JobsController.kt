package com.marla.api.controller

import com.marla.api.model.Job
import com.marla.api.service.job.RedisJobsQueue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JobsController(private val queue: RedisJobsQueue) {

    @PostMapping("/api/job")
    @ResponseBody
    fun publish(@RequestBody job: Job): String = if (this.queue.publish(job)) "ok"
    else throw RuntimeException("I don't know what to do, yet")
}