package com.grsu.schedule_project.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.model.dbo.DayDbo
import com.grsu.schedule_project.model.roomhelpers.DayWithLessons

@Dao
interface DayDao : BaseDao<DayDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg days: DayDbo) {
        deleteAll()
        insert(*days)
    }

    @Query("DELETE FROM day")
    suspend fun deleteAll()

    @Query("SELECT * FROM day")
    suspend fun getAll(): List<DayDbo>?

    @Transaction
    @Query("SELECT * FROM day")
    suspend fun getDaysWithLessons(): List<DayWithLessons>?
}