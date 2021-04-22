package com.grsu.schedule_project.core.view.vo

data class LessonTeacherVo(
    val localId: String,
    var lessonId: String? = null,
    var id: String? = null,
    var fullname: String? = null,
    var post: String? = null
)