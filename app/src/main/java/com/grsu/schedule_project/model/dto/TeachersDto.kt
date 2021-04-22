package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeachersDto(
    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "items")
    var items: List<TeacherDto>? = null
)