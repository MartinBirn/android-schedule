package com.grsu.schedule_project.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
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