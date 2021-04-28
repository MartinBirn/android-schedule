package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.dbo.TeacherDbo
import com.grsu.schedule_project.data.model.dto.TeacherDto
import com.grsu.schedule_project.data.model.vo.TeacherVo

fun TeacherDto.toTeacherDbo() = TeacherDbo(
    id = this.id,
    name = this.name,
    surname = this.surname,
    patronym = this.patronym,
    post = this.post,
    email = this.email
)

fun TeacherDbo.toTeacherVo() = TeacherVo(
    localId = this.localId,
    id = this.id,
    name = this.name,
    surname = this.surname,
    patronym = this.patronym,
    post = this.post,
    email = this.email
)