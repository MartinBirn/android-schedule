package com.grsu.schedule_project.model.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "teacher")
data class TeacherDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    var id: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var patronym: String? = null,
    var post: String? = null,
    var email: String? = null
)