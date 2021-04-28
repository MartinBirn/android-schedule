package com.grsu.schedule_project.data.model.roomhelpers

import androidx.room.Embedded
import androidx.room.Relation
import com.grsu.schedule_project.data.model.dbo.DayDbo
import com.grsu.schedule_project.data.model.dbo.LessonDbo

data class DayWithLessons(
    @Embedded val dayDbo: DayDbo,
    @Relation(
        parentColumn = "localId",
        entityColumn = "dayId"
    )
    val lessons: List<LessonDbo>
)
