package com.grsu.schedule_project.data.source.teacher.local

import com.grsu.schedule_project.data.database.dao.TeacherDao
import com.grsu.schedule_project.data.model.dbo.TeacherDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.source.teacher.TeacherDataSource

class TeacherLocalDataSource(private val teacherDao: TeacherDao) : TeacherDataSource {

    override suspend fun getTeachers(
        teacherId: String?,
        sort: String?,
        language: String?
    ): RepoResult<List<TeacherDbo>, *> = teacherDao.getAll().let(::Success)

    suspend fun insertAndDeletePrevious(vararg teachers: TeacherDbo) {
        teacherDao.insertAndDeletePrevious(*teachers)
    }

    suspend fun deleteAll() {
        teacherDao.deleteAll()
    }
}