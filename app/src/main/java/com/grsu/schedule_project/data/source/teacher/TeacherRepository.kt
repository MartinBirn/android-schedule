package com.grsu.schedule_project.data.source.teacher

import android.content.SharedPreferences
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.data.model.dbo.TeacherDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.DatabaseFailure
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.source.teacher.local.TeacherLocalDataSource
import com.grsu.schedule_project.data.source.teacher.remote.TeacherRemoteDataSource


class TeacherRepository(
    private val remoteDataSource: TeacherRemoteDataSource,
    private val localDataSource: TeacherLocalDataSource,
    private val preferences: SharedPreferences
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getTeachers(
        teacherId: String? = null,
        sort: String? = null
    ): RepoResult<List<TeacherDbo>, *> {
        var tempTeacherDboList: List<TeacherDbo>
        when (val apiResult =
            remoteDataSource.getTeachers(teacherId, sort, language = appLanguage)) {
            is Success -> tempTeacherDboList = apiResult.response
            else -> return apiResult
        }
        try {
            tempTeacherDboList =
                    //delete records with negative id's
                tempTeacherDboList.filter { teacherDbo -> teacherDbo.id?.toInt()!! > 0 }
            localDataSource.insertAndDeletePrevious(*tempTeacherDboList.toTypedArray())
            return tempTeacherDboList.let(::Success)
        } catch (e: Throwable) {
            return e.let(::DatabaseFailure)
        }
    }

    suspend fun deleteTeachers() {
        localDataSource.deleteAll()
    }
}