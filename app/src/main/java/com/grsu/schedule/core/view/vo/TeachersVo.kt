package com.grsu.schedule.core.view.vo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class TeachersVo(
    val localId: String,
    var count: Int? = null,
    var items: List<TeacherVo>? = null
)