package com.grsu.schedule_project.model.dbo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "schedule")
data class ScheduleDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    @Ignore
    var days: List<DayDbo>? = null
)