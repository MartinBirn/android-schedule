package com.grsu.schedule_project.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.data.model.dbo.DepartmentDbo

@Dao
interface DepartmentDao : BaseDao<DepartmentDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg departments: DepartmentDbo) {
        deleteAll()
        insert(*departments)
    }

    @Query("DELETE FROM department")
    suspend fun deleteAll()

    @Query("SELECT * FROM department")
    suspend fun getAll(): List<DepartmentDbo>

    @Query("SELECT * FROM department WHERE localId = :localId")
    suspend fun findById(localId: String): DepartmentDbo?
}