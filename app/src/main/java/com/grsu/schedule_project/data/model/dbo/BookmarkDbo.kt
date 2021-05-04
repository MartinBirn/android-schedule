package com.grsu.schedule_project.data.model.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkDbo(
    @PrimaryKey
    var groupId: String,
    var groupTitle: String? = null
)