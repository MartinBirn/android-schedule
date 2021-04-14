package com.grsu.schedule.core.view.vo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class GroupVo(
    val localId: String,
    var id: String? = null,
    var title: String? = null
)