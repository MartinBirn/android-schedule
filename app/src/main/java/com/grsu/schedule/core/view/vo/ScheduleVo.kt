package com.grsu.schedule.core.view.vo

data class ScheduleVo(
    val localId: String,
    var days: List<DayVo>? = null
)