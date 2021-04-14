package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class ScheduleDto(
    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "days")
    var days: List<DayDto>? = null
)