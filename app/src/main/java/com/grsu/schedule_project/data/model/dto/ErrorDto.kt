package com.grsu.schedule_project.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorDto(
    @Json(name = "code")
    var code: String? = null,

    @Json(name = "desc")
    var description: String? = null,

    @Json(name = "details")
    var details: String? = null
)
