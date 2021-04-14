package com.grsu.schedule.model.dto

import com.squareup.moshi.Json

data class TeacherDto(
    @Json(name = "id")
    var id: String? = null,

    @Json(name = "name")
    var name: String? = null,

    @Json(name = "surname")
    var surname: String? = null,

    @Json(name = "patronym")
    var patronym: String? = null,

    @Json(name = "post")
    var post: String? = null,

    @Json(name = "phone")
    var phone: String? = null,

    @Json(name = "descr")
    var descr: String? = null,

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "skype")
    var skype: String? = null
)