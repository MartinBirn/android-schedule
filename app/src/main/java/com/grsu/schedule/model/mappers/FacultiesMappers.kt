package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.FacultiesVo
import com.grsu.schedule.model.dbo.FacultiesDbo
import com.grsu.schedule.model.dto.FacultiesDto

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