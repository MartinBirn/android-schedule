package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.FacultyVo
import com.grsu.schedule_project.model.dbo.FacultyDbo
import com.grsu.schedule_project.model.dto.FacultyDto

fun FacultyDto.toFacultyDbo() = FacultyDbo(
    id = this.id,
    title = this.title
)

fun FacultyDbo.toFacultyVo() = FacultyVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)