package com.grsu.schedule_project.model.dbo

import androidx.room.*
import java.util.*

@Entity(
    tableName = "lesson",
    foreignKeys = [ForeignKey(
        entity = DayDbo::class,
        parentColumns = ["localId"],
        childColumns = ["dayId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class LessonDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    @ColumnInfo(index = true)
    var dayId: String? = null,
    var timeStart: String? = null,
    var timeEnd: String? = null,
    @Embedded(prefix = "lessonTeacher")
    var teacher: LessonTeacherDbo? = null,
    var label: String? = null,
    var type: String? = null,
    var title: String? = null,
    var address: String? = null,
    var room: String? = null,
    @Embedded(prefix = "subgroup")
    var subGroup: SubGroupDbo? = null
)