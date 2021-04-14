package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class DepartmentsDto(
    @Json(name = "items")
    var items: List<DepartmentDto>? = null
)