package com.marla.api.service.job

import com.marla.api.config.GatekeeperConfiguration
import com.marla.api.model.Enabled
import com.marla.api.model.Job
import com.marla.api.model.JobRequestStatus
import com.marla.api.model.JobResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class JobsService(
    private val queue: RedisJobsQueue,
    private val restTemplate: RestTemplate,
    private val gatekeeperConfiguration: GatekeeperConfiguration
) {

    fun publishJob(job: Job, headers: Map<String, String>): JobResponse {
        val enabled = restTemplate.exchange(
            "${gatekeeperConfiguration.endpoint}/",
            HttpMethod.GET,
            HttpEntity(headers),
            Enabled::class.java
        ).body
        if (!enabled!!.isEnabled) // TODO we intentionally want this to crash if body is null
            return JobResponse(JobRequestStatus.REJECTED)

        return if (this.queue.publish(job)) JobResponse(JobRequestStatus.OK)
        else throw RuntimeException("I don't know what to do, yet")
    }
}