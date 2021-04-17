package com.grsu.schedule_project.core.view.vo

data class ScheduleVo(
    val localId: String,
    var days: List<DayVo>? = null
)