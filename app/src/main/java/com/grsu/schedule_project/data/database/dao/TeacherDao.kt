package com.grsu.schedule_project.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.data.model.dbo.TeacherDbo

@Dao
interface TeacherDao : BaseDao<TeacherDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg teachers: TeacherDbo) {
        deleteAll()
        insert(*teachers)
    }

    @Query("DELETE FROM teacher")
    suspend fun deleteAll()

    @Query("SELECT * FROM teacher")
    suspend fun getAll(): List<TeacherDbo>

    @Query("SELECT * FROM teacher WHERE localId = :localId")
    suspend fun findById(localId: String): TeacherDbo?
}