package com.grsu.schedule_project.data.model.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "group")
data class GroupDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    var id: String? = null,
    var title: String? = null
)