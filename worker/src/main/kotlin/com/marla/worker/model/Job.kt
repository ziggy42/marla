package com.marla.worker.model

data class Job(val clientId: String, val source: String, val destination: String, val amount: Double)