package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class TeachersDto(
    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "items")
    var items: List<TeacherDto>? = null
) {
    enum class SORT {
        SURNAME,
        NAME,
        PATRONYM,
        POST;

        override fun toString(): String {
            return super.toString().toLowerCase(Locale.ROOT)
        }
    }
}