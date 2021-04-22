package com.grsu.schedule_project.model.dbo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "groups")
data class GroupsDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    @Ignore
    var items: List<GroupDbo>? = null
)