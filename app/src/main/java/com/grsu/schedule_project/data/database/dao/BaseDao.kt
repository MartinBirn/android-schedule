package com.grsu.schedule_project.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert
    suspend fun insert(vararg obj: T)

    @Update
    suspend fun update(vararg obj: T)

    @Delete
    suspend fun delete(vararg obj: T)
}