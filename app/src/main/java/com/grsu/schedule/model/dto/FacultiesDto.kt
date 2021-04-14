package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class FacultiesDto(
    @Json(name = "items")
    var items: List<FacultyDto>? = null
)