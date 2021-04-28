package com.grsu.schedule_project.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DepartmentsDto(
    @Json(name = "items")
    var items: List<DepartmentDto>? = null
)