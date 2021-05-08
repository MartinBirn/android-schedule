package com.grsu.schedule_project.data.source.department

import android.content.SharedPreferences
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.data.model.dbo.DepartmentDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.DatabaseFailure
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.source.department.local.DepartmentLocalDataSource
import com.grsu.schedule_project.data.source.department.remote.DepartmentRemoteDataSource

class DepartmentRepository(
    private val remoteDataSource: DepartmentRemoteDataSource,
    private val localDataSource: DepartmentLocalDataSource,
    private val preferences: SharedPreferences
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getDepartments(): RepoResult<List<DepartmentDbo>, *> {
        val departmentDboList = localDataSource.getDepartments()
        if (departmentDboList is Success && departmentDboList.response.isEmpty()) {
            val tempDepartmentDboList: List<DepartmentDbo>
            when (val apiResult = remoteDataSource.getDepartments(language = appLanguage)) {
                is Success -> tempDepartmentDboList = apiResult.response
                else -> return apiResult
            }
            return try {
                localDataSource.insert(*tempDepartmentDboList.toTypedArray())
                tempDepartmentDboList.let(::Success)
            } catch (e: Throwable) {
                e.let(::DatabaseFailure)
            }
        }
        return departmentDboList
    }

    suspend fun deleteDepartments() {
        localDataSource.deleteAll()
    }
}