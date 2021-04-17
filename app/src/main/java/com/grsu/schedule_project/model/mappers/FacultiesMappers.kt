package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.FacultiesVo
import com.grsu.schedule_project.model.dbo.FacultiesDbo
import com.grsu.schedule_project.model.dto.FacultiesDto

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