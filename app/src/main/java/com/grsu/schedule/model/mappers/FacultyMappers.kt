package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.FacultyVo
import com.grsu.schedule.model.dbo.FacultyDbo
import com.grsu.schedule.model.dto.FacultyDto

fun FacultyDto.toFacultyDbo() = FacultyDbo(
    id = this.id,
    title = this.title
)

fun FacultyDbo.toFacultyVo() = FacultyVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)