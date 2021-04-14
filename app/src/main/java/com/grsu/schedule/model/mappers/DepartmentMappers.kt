package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.DepartmentVo
import com.grsu.schedule.model.dbo.DepartmentDbo
import com.grsu.schedule.model.dto.DepartmentDto

fun DepartmentDto.toDepartmentDbo() = DepartmentDbo(
    id = this.id,
    title = this.title
)

fun DepartmentDbo.toDepartmentVo() = DepartmentVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)