package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.TeacherVo
import com.grsu.schedule_project.model.dbo.TeacherDbo
import com.grsu.schedule_project.model.dto.TeacherDto

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