package com.marla.api.model

enum class JobRequestStatus { OK, REJECTED }

data class JobResponse(val status: JobRequestStatus)