package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class LessonDto(
    @Json(name = "timeStart")
    var timeStart: String? = null,

    @Json(name = "timeEnd")
    var timeEnd: String? = null,

    @Json(name = "teacher")
    var teacher: TeacherDto? = null,

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