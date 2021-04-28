package com.grsu.schedule_project.data.model.dbo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "faculties")
data class FacultiesDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    @Ignore
    var items: List<FacultyDbo>? = null
)