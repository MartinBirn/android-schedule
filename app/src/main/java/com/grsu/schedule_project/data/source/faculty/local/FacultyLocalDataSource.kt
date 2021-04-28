package com.grsu.schedule_project.data.source.faculty.local

import com.grsu.schedule_project.data.database.dao.FacultyDao
import com.grsu.schedule_project.data.model.dbo.FacultyDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.source.faculty.FacultyDataSource

class FacultyLocalDataSource(private val facultyDao: FacultyDao) : FacultyDataSource {

    override suspend fun getFaculties(language: String?): RepoResult<List<FacultyDbo>, *> =
        facultyDao.getAll().let(::Success)

    suspend fun insertAndDeletePrevious(vararg faculties: FacultyDbo) {
        facultyDao.insertAndDeletePrevious(*faculties)
    }

    suspend fun deleteAll() {
        facultyDao.deleteAll()
    }
}