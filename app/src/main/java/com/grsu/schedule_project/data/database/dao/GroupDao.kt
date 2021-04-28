package com.grsu.schedule_project.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.data.model.dbo.GroupDbo

@Dao
interface GroupDao : BaseDao<GroupDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg groups: GroupDbo) {
        deleteAll()
        insert(*groups)
    }

    @Query("DELETE FROM `group`")
    suspend fun deleteAll()

    @Query("SELECT * FROM `group`")
    suspend fun getAll(): List<GroupDbo>

    @Query("SELECT * FROM `group` WHERE localId = :localId")
    suspend fun findById(localId: String): GroupDbo?
}