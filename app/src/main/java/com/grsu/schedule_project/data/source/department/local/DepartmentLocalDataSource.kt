package com.grsu.schedule_project.data.source.department.local

import com.grsu.schedule_project.data.database.dao.DepartmentDao
import com.grsu.schedule_project.data.model.dbo.DepartmentDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.source.department.DepartmentDataSource

class DepartmentLocalDataSource(private val departmentDao: DepartmentDao) : DepartmentDataSource {

    override suspend fun getDepartments(language: String?): RepoResult<List<DepartmentDbo>, *> =
        departmentDao.getAll().let(::Success)

    suspend fun insertAndDeletePrevious(vararg departments: DepartmentDbo) {
        departmentDao.insertAndDeletePrevious(*departments)
    }

    suspend fun deleteAll() {
        departmentDao.deleteAll()
    }
}