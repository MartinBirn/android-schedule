package com.grsu.schedule.core.view.vo

data class TeachersVo(
    val localId: String,
    var count: Int? = null,
    var items: List<TeacherVo>? = null
)