package com.grsu.schedule.core.view.vo

data class DayVo(
    var localId: String,
    var num: String? = null,
    var date: String? = null,
    var lessons: List<LessonVo>? = null
)