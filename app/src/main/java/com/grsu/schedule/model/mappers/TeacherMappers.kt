package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.TeacherVo
import com.grsu.schedule.model.dbo.TeacherDbo
import com.grsu.schedule.model.dto.TeacherDto

fun TeacherDto.toTeacherDbo() = TeacherDbo(
    id = this.id,
    name = this.name,
    surname = this.surname,
    patronym = this.patronym,
    post = this.post,
    phone = this.phone,
    descr = this.descr,
    email = this.email,
    skype = this.skype
)

fun TeacherDbo.toTeacherVo() = TeacherVo(
    localId = this.localId,
    id = this.id,
    name = this.name,
    surname = this.surname,
    patronym = this.patronym,
    post = this.post,
    phone = this.phone,
    descr = this.descr,
    email = this.email,
    skype = this.skype
)