package com.grsu.schedule_project.data.model.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bookmark")
data class BookmarkDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    var groupId: String,
    var groupTitle: String? = null
)