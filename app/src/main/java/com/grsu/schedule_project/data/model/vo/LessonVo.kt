package com.grsu.schedule_project.data.model.vo

data class LessonVo(
    val localId: String,
    var dayId: String? = null,
    var timeStart: String? = null,
    var timeEnd: String? = null,
    var teacher: LessonTeacherVo? = null,
    var label: String? = null,
    var type: String? = null,
    var title: String? = null,
    var address: String? = null,
    var room: String? = null,
    var subGroup: SubGroupVo? = null
)