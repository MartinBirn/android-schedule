package com.grsu.schedule_project.model.dbo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "day")
data class DayDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    var num: String? = null,
    var date: String? = null,
    @Ignore
    var lessons: List<LessonDbo>? = null
)