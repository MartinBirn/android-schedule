package com.grsu.schedule_project.data.model.vo

data class LessonTeacherVo(
    val localId: String,
    var lessonId: String? = null,
    var id: String? = null,
    var fullname: String? = null,
    var post: String? = null
)