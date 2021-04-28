package com.grsu.schedule_project.data.model.vo

data class DepartmentsVo(
    val localId: String,
    var items: List<DepartmentVo>? = null
)