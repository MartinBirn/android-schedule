package com.grsu.schedule_project.model.dbo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class TeachersDbo(
    @PrimaryKey
    val localId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "count")
    var count: Int? = null,

    @ColumnInfo(name = "items")
    var items: List<TeacherDbo>? = null
)