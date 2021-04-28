package com.grsu.schedule_project.data.source.group

import com.grsu.schedule_project.data.model.errorhandling.RepoResult

interface GroupDataSource {

    suspend fun getGroups(
        departmentId: String,
        facultyId: String,
        course: String,
        language: String? = null
    ): RepoResult<*, *>
}