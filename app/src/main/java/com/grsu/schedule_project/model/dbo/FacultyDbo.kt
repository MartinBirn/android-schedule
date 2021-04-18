package com.grsu.schedule_project.model.dbo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class FacultyDbo(
    @PrimaryKey
    val localId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "id")
    var id: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null
)