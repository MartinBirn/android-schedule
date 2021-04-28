package com.grsu.schedule_project.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.data.model.dbo.LessonDbo

@Dao
interface LessonDao : BaseDao<LessonDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg lessons: LessonDbo) {
        deleteAll()
        insert(*lessons)
    }

    @Query("DELETE FROM lesson")
    suspend fun deleteAll()

    @Query("SELECT * FROM lesson")
    suspend fun getAll(): List<LessonDbo>
}