package com.grsu.schedule_project.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.model.dbo.LessonTeacherDbo

@Dao
interface LessonTeacherDao : BaseDao<LessonTeacherDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg lessonTeachers: LessonTeacherDbo) {
        deleteAll()
        insert(*lessonTeachers)
    }

    @Query("DELETE FROM lessonTeacher")
    suspend fun deleteAll()

    @Query("SELECT * FROM lessonTeacher")
    suspend fun getAll(): List<LessonTeacherDbo>?
}