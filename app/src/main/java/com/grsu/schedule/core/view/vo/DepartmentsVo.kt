package com.grsu.schedule.core.view.vo

data class DepartmentsVo(
    val localId: String,
    var items: List<DepartmentVo>? = null
)