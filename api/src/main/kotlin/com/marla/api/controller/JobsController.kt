package com.marla.api.controller

import com.marla.api.model.Job
import com.marla.api.model.JobResponse
import com.marla.api.service.job.JobsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JobsController(private val jobsService: JobsService) {

    @PostMapping("/api/job")
    @ResponseBody
    fun publish(
        @RequestBody job: Job,
        @RequestHeader("x-request-id") xreq: String,
        @RequestHeader("x-b3-traceid") xtraceid: String,
        @RequestHeader("x-b3-spanid") xspanid: String,
        @RequestHeader("x-b3-parentspanid") xparentspanid: String,
        @RequestHeader("x-b3-sampled") xsampled: String,
        @RequestHeader("x-b3-flags") xflags: String,
        @RequestHeader("x-ot-span-context") xotspan: String
    ): JobResponse = jobsService.publishJob(
        job, mapOf(
            "x-request-id" to xreq,
            "x-b3-traceid" to xtraceid,
            "x-b3-spanid" to xspanid,
            "x-b3-parentspanid" to xparentspanid,
            "x-b3-sampled" to xsampled,
            "x-b3-flags" to xflags,
            "x-ot-span-context" to xotspan
        )
    )
}