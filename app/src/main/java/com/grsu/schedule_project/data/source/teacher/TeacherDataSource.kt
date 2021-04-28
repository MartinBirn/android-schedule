package com.grsu.schedule_project.data.source.teacher

import com.grsu.schedule_project.data.model.errorhandling.RepoResult

interface TeacherDataSource {

    suspend fun getTeachers(
        teacherId: String? = null,
        sort: String? = null,
        language: String? = null
    ): RepoResult<*, *>
}