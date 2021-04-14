package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class DayDto(
    @Json(name = "num")
    var num: String? = null,

    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "date")
    var date: String? = null,

    @Json(name = "lessons")
    var lessons: List<LessonDto>? = null
)