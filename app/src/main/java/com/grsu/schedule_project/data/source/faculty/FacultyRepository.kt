package com.grsu.schedule_project.data.source.faculty

import android.content.SharedPreferences
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.data.model.dto.FacultyDto
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.DatabaseFailure
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.model.mappers.toFacultyDbo
import com.grsu.schedule_project.data.source.faculty.local.FacultyLocalDataSource
import com.grsu.schedule_project.data.source.faculty.remote.FacultyRemoteDataSource

class FacultyRepository(
    private val remoteDataSource: FacultyRemoteDataSource,
    private val localDataSource: FacultyLocalDataSource,
    private val preferences: SharedPreferences
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getFaculties(): RepoResult<*, *> {
        val facultyDboList = localDataSource.getFaculties()
        if (facultyDboList is Success && facultyDboList.response.isEmpty()) {
            val tempFacultyDtoList: List<FacultyDto>
            when (val apiResult = remoteDataSource.getFaculties(language = appLanguage)) {
                is Success -> tempFacultyDtoList = apiResult.response
                else -> return apiResult
            }
            return try {
                val tempFacultyDboList = tempFacultyDtoList.map { it.toFacultyDbo() }
                localDataSource.insertAndDeletePrevious(*tempFacultyDboList.toTypedArray())
                tempFacultyDboList.let(::Success)
            } catch (e: Throwable) {
                e.let(::DatabaseFailure)
            }
        }
        return facultyDboList
    }

    suspend fun deleteFaculties() {
        localDataSource.deleteAll()
    }
}