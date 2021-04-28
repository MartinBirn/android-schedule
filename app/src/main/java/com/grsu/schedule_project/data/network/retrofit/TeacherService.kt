package com.grsu.schedule_project.data.network.retrofit

import com.grsu.schedule_project.data.model.dto.ErrorWrapperDto
import com.grsu.schedule_project.data.model.dto.TeachersDto
import com.grsu.schedule_project.data.network.GET_TEACHERS_PATH
import com.grsu.schedule_project.data.network.LANGUAGE_PATH_KEY
import com.grsu.schedule_project.data.network.SORT_PATH_KEY
import com.grsu.schedule_project.data.network.TEACHER_ID_PATH_KEY
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TeacherService {

    @GET(GET_TEACHERS_PATH)
    suspend fun getTeachers(
        @Query(TEACHER_ID_PATH_KEY) teacherId: String? = null,
        @Query(SORT_PATH_KEY) sort: String? = null,
        @Query(LANGUAGE_PATH_KEY) language: String? = null
    ): ApiResult<TeachersDto, ErrorWrapperDto>
}