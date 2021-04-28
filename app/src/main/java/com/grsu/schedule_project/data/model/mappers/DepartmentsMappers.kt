package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.vo.DepartmentsVo
import com.grsu.schedule_project.data.model.dbo.DepartmentsDbo
import com.grsu.schedule_project.data.model.dto.DepartmentsDto

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