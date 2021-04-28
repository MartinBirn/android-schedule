package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.dbo.FacultiesDbo
import com.grsu.schedule_project.data.model.dto.FacultiesDto
import com.grsu.schedule_project.data.model.vo.FacultiesVo

fun FacultiesDto.toFacultiesDbo() = FacultiesDbo(
    items = this.items?.map { facultyDto ->
        facultyDto.toFacultyDbo()
    }
)

fun FacultiesDbo.toFacultiesVo() = FacultiesVo(
    localId = this.localId,
    items = this.items?.map { facultyDbo ->
        facultyDbo.toFacultyVo()
    }
)