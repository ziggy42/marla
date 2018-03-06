package com.marla.api.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class Job(val amount: Double, val sender: String, val receiver: String) {

    fun toJSON(): String = jacksonObjectMapper().writeValueAsString(this)
}