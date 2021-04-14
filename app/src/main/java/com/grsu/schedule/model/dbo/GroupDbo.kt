package com.grsu.schedule.model.dbo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class GroupDbo(
    @PrimaryKey
    val localId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "id")
    var id: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null
)