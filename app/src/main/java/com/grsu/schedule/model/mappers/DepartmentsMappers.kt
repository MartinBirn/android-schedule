package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.DepartmentsVo
import com.grsu.schedule.model.dbo.DepartmentsDbo
import com.grsu.schedule.model.dto.DepartmentsDto

fun DepartmentsDto.toDepartmentsDbo() = DepartmentsDbo(
    items = this.items?.map { departmentDto ->
        departmentDto.toDepartmentDbo()
    }
)

fun DepartmentsDbo.toDepartmentsVo() = DepartmentsVo(
    localId = this.localId,
    items = this.items?.map { departmentDbo ->
        departmentDbo.toDepartmentVo()
    }
)