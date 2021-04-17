package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json

data class ScheduleDto(
    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "days")
    var days: List<DayDto>? = null
)