package com.grsu.schedule_project.model.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "lessonTeacher")
data class LessonTeacherDbo(
    @PrimaryKey
    var localId: String = UUID.randomUUID().toString(),
    var lessonId: String? = null,
    var id: String? = null,
    var fullname: String? = null,
    var post: String? = null
)