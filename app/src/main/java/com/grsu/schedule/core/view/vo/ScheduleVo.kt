package com.grsu.schedule.core.view.vo

import java.util.*

data class ScheduleVo(
    val localId: String,
    var days: DayVo? = null
)