package com.grsu.schedule_project.data.source.group.local

import com.grsu.schedule_project.data.database.dao.GroupDao
import com.grsu.schedule_project.data.model.dbo.GroupDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.source.group.GroupDataSource

class GroupLocalDataSource(private val groupDao: GroupDao) : GroupDataSource {

    override suspend fun getGroups(
        departmentId: String,
        facultyId: String,
        course: String,
        language: String?
    ): RepoResult<List<GroupDbo>, *> =
        groupDao.getAll().let(::Success)

    suspend fun insert(vararg groups: GroupDbo) {
        groupDao.insert(*groups)
    }

    suspend fun insertAndDeletePrevious(vararg groups: GroupDbo) {
        groupDao.insertAndDeletePrevious(*groups)
    }

    suspend fun deleteAll() {
        groupDao.deleteAll()
    }
}