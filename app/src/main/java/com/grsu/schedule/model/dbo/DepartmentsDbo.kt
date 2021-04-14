package com.grsu.schedule.model.dbo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class DepartmentsDbo(
    @PrimaryKey
    val localId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "items")
    var items: List<DepartmentDbo>? = null
)