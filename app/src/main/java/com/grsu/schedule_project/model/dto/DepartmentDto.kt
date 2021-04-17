package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json

data class DepartmentDto(
    @Json(name = "id")
    var id: String? = null,

    @Json(name = "title")
    var title: String? = null
)