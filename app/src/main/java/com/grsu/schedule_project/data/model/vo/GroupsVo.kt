package com.grsu.schedule_project.data.model.vo

data class GroupsVo(
    val localId: String,
    var items: List<GroupVo>? = null
)