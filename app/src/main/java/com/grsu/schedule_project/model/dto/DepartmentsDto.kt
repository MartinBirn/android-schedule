package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json

data class DepartmentsDto(
    @Json(name = "items")
    var items: List<DepartmentDto>? = null
)