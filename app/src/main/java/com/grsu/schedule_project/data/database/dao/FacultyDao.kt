package com.grsu.schedule_project.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.data.model.dbo.FacultyDbo

@Dao
interface FacultyDao : BaseDao<FacultyDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg faculties: FacultyDbo) {
        deleteAll()
        insert(*faculties)
    }

    @Query("DELETE FROM faculty")
    suspend fun deleteAll()

    @Query("SELECT * FROM faculty")
    suspend fun getAll(): List<FacultyDbo>

    @Query("SELECT * FROM faculty WHERE localId = :localId")
    suspend fun findById(localId: String): FacultyDbo?
}