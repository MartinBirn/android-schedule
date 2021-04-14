package com.grsu.schedule.core.view.vo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class DepartmentsVO(
    val localId: String,
    var items: List<DepartmentVo>? = null
)