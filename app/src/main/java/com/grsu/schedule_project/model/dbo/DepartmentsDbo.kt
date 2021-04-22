package com.grsu.schedule_project.model.dbo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "departments")
data class DepartmentsDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    @Ignore
    var items: List<DepartmentDbo>? = null
)