package com.marla.api.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class Job(val clientId: String, val source: String, val destination: String, val amount: Double) {

    fun toJSON(): String = jacksonObjectMapper().writeValueAsString(this)
}