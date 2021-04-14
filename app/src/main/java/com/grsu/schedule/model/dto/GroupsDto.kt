package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class GroupsDto(
    @Json(name = "items")
    var items: List<GroupDto>? = null
)