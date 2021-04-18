package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.DepartmentVo
import com.grsu.schedule_project.model.dbo.DepartmentDbo
import com.grsu.schedule_project.model.dto.DepartmentDto

fun DepartmentDto.toDepartmentDbo() = DepartmentDbo(
    id = this.id,
    title = this.title
)

fun DepartmentDbo.toDepartmentVo() = DepartmentVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)