package com.grsu.schedule_project.network.retrofit

import com.grsu.schedule_project.model.dto.*
import com.grsu.schedule_project.network.*
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface ApiService {

    @GET(GET_FACULTIES_PATH)
    suspend fun getFaculties(): ApiResult<FacultiesDto, ErrorWrapperDto>

    @GET(GET_DEPARTMENTS_PATH)
    suspend fun getDepartments(): ApiResult<DepartmentsDto, ErrorWrapperDto>

    @GET(GET_GROUPS_PATH)
    suspend fun getGroups(
        @Query(DEPARTMENT_ID_PATH_KEY) departmentId: Int,
        @Query(FACULTY_ID_PATH_KEY) facultyId: Int,
        @Query(COURSE_PATH_KEY) course: Int
    ): ApiResult<GroupsDto, ErrorWrapperDto>

    @GET(GET_TEACHERS_PATH)
    suspend fun getTeachers(
        @Query(TEACHER_ID_PATH_KEY) teacherId: Int? = null,
        @Query(SORT_PATH_KEY) sort: TeachersDto.SORT? = null
    ): ApiResult<TeachersDto, ErrorWrapperDto>

    @GET(GET_GROUP_SCHEDULE_PATH)
    suspend fun getGroupSchedule(
        @Query(GROUP_ID_PATH_KEY) groupId: Int,
        @Query(DATE_START_PATH_KEY) dateStart: LocalDate? = null,
        @Query(DATE_END_PATH_KEY) dateEnd: LocalDate? = null
    ): ApiResult<ScheduleDto, ErrorWrapperDto>
}