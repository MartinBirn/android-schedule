package com.grsu.schedule.core.view.vo

data class LessonVo(
    val localId: String,
    var timeStart: String? = null,
    var timeEnd: String? = null,
    var teacher: TeacherVo? = null,
    var label: String? = null,
    var type: String? = null,
    var title: String? = null,
    var address: String? = null,
    var room: String? = null,
    var subGroup: SubGroupVo? = null
)