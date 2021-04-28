package com.grsu.schedule_project.data.model.vo

data class ScheduleVo(
    val localId: String,
    var days: List<DayVo>? = null
)