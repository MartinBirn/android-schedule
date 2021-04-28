package com.grsu.schedule_project.data.source.department

import com.grsu.schedule_project.data.model.errorhandling.RepoResult

interface DepartmentDataSource {

    suspend fun getDepartments(language: String? = null): RepoResult<*, *>
}