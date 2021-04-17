package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FacultiesDto(
    @Json(name = "items")
    var items: List<FacultyDto>? = null
)