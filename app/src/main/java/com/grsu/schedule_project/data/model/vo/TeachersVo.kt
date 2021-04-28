package com.grsu.schedule_project.data.model.vo

data class TeachersVo(
    val localId: String,
    var count: Int? = null,
    var items: List<TeacherVo>? = null
)