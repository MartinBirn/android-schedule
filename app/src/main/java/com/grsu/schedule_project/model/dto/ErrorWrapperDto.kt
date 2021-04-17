package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorWrapperDto(
    @Json(name = "error")
    var error: ErrorDto? = null
)