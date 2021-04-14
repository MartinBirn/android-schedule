package com.grsu.schedule.model.dbo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.grsu.schedule.model.dto.FacultyDto
import java.util.*

data class FacultiesDbo(
    @PrimaryKey
    val localId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "items")
    var items: List<FacultyDbo>? = null
)