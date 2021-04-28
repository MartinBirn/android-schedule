package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.dbo.TeachersDbo
import com.grsu.schedule_project.data.model.dto.TeachersDto
import com.grsu.schedule_project.data.model.vo.TeachersVo

fun TeachersDto.toTeachersDbo() = TeachersDbo(
    count = this.count,
    items = this.items?.map { teacherDto ->
        teacherDto.toTeacherDbo()
    }
)

fun TeachersDbo.toTeachersVo() = TeachersVo(
    localId = this.localId,
    count = this.count,
    items = this.items?.map { teacherDto ->
        teacherDto.toTeacherVo()
    }
)