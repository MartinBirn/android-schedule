package com.grsu.schedule_project.model.dbo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "teachers")
data class TeachersDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    var count: Int? = null,
    @Ignore
    var items: List<TeacherDbo>? = null
)