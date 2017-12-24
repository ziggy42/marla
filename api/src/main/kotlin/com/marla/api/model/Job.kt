package com.marla.api.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * A job to be published on the queue
 *
 * @param clientId The id of the client owning the job
 * @param script The content of the job
 * @constructor Creates an instance of a [Job]
 */
data class Job(val clientId: String, val script: String) {

    fun toJSON(): String = jacksonObjectMapper().writeValueAsString(this)
}