package com.grsu.schedule.model.dbo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class GroupsDbo(
    @PrimaryKey
    val localId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "items")
    var items: List<GroupDbo>? = null
)