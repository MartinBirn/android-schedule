package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class FacultyDto(
    @Json(name = "id")
    var id: String? = null,

    @Json(name = "title")
    var title: String? = null
)