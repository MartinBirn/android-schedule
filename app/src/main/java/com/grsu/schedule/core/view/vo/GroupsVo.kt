package com.grsu.schedule.core.view.vo

data class GroupsVo(
    val localId: String,
    var items: List<GroupVo>? = null
)