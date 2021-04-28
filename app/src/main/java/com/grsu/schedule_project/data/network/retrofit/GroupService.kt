package com.grsu.schedule_project.data.network.retrofit

import com.grsu.schedule_project.data.model.dto.ErrorWrapperDto
import com.grsu.schedule_project.data.model.dto.GroupsDto
import com.grsu.schedule_project.data.network.*
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupService {

    @GET(GET_GROUPS_PATH)
    suspend fun getGroups(
        @Query(DEPARTMENT_ID_PATH_KEY) departmentId: String,
        @Query(FACULTY_ID_PATH_KEY) facultyId: String,
        @Query(COURSE_PATH_KEY) course: String,
        @Query(LANGUAGE_PATH_KEY) language: String? = null
    ): ApiResult<GroupsDto, ErrorWrapperDto>
}