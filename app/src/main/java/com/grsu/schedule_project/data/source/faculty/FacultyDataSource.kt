package com.grsu.schedule_project.data.source.faculty

import com.grsu.schedule_project.data.model.errorhandling.RepoResult

interface FacultyDataSource {

    suspend fun getFaculties(language: String? = null): RepoResult<*, *>
}