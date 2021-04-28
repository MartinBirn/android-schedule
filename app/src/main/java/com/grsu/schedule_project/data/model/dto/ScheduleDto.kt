package com.grsu.schedule_project.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleDto(
    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "days")
    var days: List<DayDto>? = null
)