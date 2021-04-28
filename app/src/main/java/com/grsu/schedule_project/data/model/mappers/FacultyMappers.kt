package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.vo.FacultyVo
import com.grsu.schedule_project.data.model.dbo.FacultyDbo
import com.grsu.schedule_project.data.model.dto.FacultyDto

fun FacultyDto.toFacultyDbo() = FacultyDbo(
    id = this.id,
    title = this.title
)

fun FacultyDbo.toFacultyVo() = FacultyVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)