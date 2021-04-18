package com.grsu.schedule_project.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupDto(
    @Json(name = "id")
    var id: String? = null,

    @Json(name = "title")
    var title: String? = null
)