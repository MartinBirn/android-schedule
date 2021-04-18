package com.grsu.schedule_project.model.dbo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class LessonDbo(
    @PrimaryKey
    val localId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "timeStart")
    var timeStart: String? = null,

    @ColumnInfo(name = "timeEnd")
    var timeEnd: String? = null,

    @ColumnInfo(name = "teacher")
    var teacher: TeacherDbo? = null,

    @ColumnInfo(name = "label")
    var label: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "address")
    var address: String? = null,

    @ColumnInfo(name = "room")
    var room: String? = null,

    @ColumnInfo(name = "subgroup")
    var subGroup: SubGroupDbo? = null
)