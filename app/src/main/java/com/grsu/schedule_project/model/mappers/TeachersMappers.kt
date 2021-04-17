package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.TeachersVo
import com.grsu.schedule_project.model.dbo.TeachersDbo
import com.grsu.schedule_project.model.dto.TeachersDto

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