package com.grsu.schedule_project.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupsDto(
    @Json(name = "items")
    var items: List<GroupDto>? = null
)