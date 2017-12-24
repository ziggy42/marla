package com.marla.api.service.job

import com.marla.api.model.Job

/**
 * A JobsQueue is a data structure to publish new [Job]
 */
interface JobsQueue {

    /**
     * Publish a new job to the queue
     *
     * @param job The new job to publish
     * @return True if the operation was successful
     */
    fun publish(job: Job): Boolean
}