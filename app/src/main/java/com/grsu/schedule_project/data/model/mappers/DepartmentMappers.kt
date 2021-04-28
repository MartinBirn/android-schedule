package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.dbo.DepartmentDbo
import com.grsu.schedule_project.data.model.dto.DepartmentDto
import com.grsu.schedule_project.data.model.vo.DepartmentVo

fun DepartmentDto.toDepartmentDbo() = DepartmentDbo(
    id = this.id,
    title = this.title
)

fun DepartmentDbo.toDepartmentVo() = DepartmentVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)