package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.DepartmentsVo
import com.grsu.schedule_project.model.dbo.DepartmentsDbo
import com.grsu.schedule_project.model.dto.DepartmentsDto

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