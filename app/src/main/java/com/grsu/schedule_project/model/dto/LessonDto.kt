package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LessonDto(
    @Json(name = "timeStart")
    var timeStart: String? = null,

    @Json(name = "timeEnd")
    var timeEnd: String? = null,

    @Json(name = "teacher")
    var teacher: LessonTeacherDto? = null,

    @Json(name = "label")
    var label: String? = null,

    @Json(name = "type")
    var type: String? = null,

    @Json(name = "title")
    var title: String? = null,

    @Json(name = "address")
    var address: String? = null,

    @Json(name = "room")
    var room: String? = null,

    @Json(name = "subgroup")
    var subGroup: SubGroupDto? = null
)