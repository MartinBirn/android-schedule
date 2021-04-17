package com.grsu.schedule_project.core.view.vo

data class TeacherVo(
    val localId: String,
    var id: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var patronym: String? = null,
    var post: String? = null,
    var phone: String? = null,
    var descr: String? = null,
    var email: String? = null,
    var skype: String? = null
)