package com.grsu.schedule_project.data.source.teacher

import android.content.SharedPreferences
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.data.model.dto.TeacherDto
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.DatabaseFailure
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.model.mappers.toTeacherDbo
import com.grsu.schedule_project.data.source.teacher.local.TeacherLocalDataSource
import com.grsu.schedule_project.data.source.teacher.remote.TeacherRemoteDataSource


class TeacherRepository(
    private val remoteDataSource: TeacherRemoteDataSource,
    private val localDataSource: TeacherLocalDataSource,
    private val preferences: SharedPreferences
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getTeachers(teacherId: String? = null, sort: String? = null): RepoResult<*, *> {
        val teacherDboList = localDataSource.getTeachers()
        if (teacherDboList is Success && teacherDboList.response.isEmpty()) {
            val tempTeacherDtoList: List<TeacherDto>
            when (val apiResult =
                remoteDataSource.getTeachers(
                    teacherId = teacherId,
                    sort = sort,
                    language = appLanguage
                )) {
                is Success -> tempTeacherDtoList = apiResult.response
                else -> return apiResult
            }
            try {
                val tempTeacherDboList =
                    tempTeacherDtoList.map { teacherDto -> teacherDto.toTeacherDbo() }
                        //delete records with negative id's
                        .filter { teacherDbo -> teacherDbo.id?.toInt()!! > 0 }
                localDataSource.insertAndDeletePrevious(*tempTeacherDboList.toTypedArray())
                return tempTeacherDtoList.let(::Success)
            } catch (e: Throwable) {
                return e.let(::DatabaseFailure)
            }
        }
        return teacherDboList
    }

    suspend fun deleteTeachers() {
        localDataSource.deleteAll()
    }
}