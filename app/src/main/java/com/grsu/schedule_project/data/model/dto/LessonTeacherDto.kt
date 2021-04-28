package com.grsu.schedule_project.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LessonTeacherDto(
    @Json(name = "id")
    var id: String? = null,

    @Json(name = "fullname")
    var fullname: String? = null,

    @Json(name = "post")
    var post: String? = null
)