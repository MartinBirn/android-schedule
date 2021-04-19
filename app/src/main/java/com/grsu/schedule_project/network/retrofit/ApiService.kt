package com.grsu.schedule_project.network.retrofit

import com.grsu.schedule_project.model.dto.*
import com.grsu.schedule_project.network.*
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface ApiService {

    @GET(GET_FACULTIES_PATH)
    suspend fun getFaculties(@Query(LANGUAGE_PATH_KEY) language: String? = null): ApiResult<FacultiesDto, ErrorWrapperDto>

    @GET(GET_DEPARTMENTS_PATH)
    suspend fun getDepartments(@Query(LANGUAGE_PATH_KEY) language: String? = null): ApiResult<DepartmentsDto, ErrorWrapperDto>

    @GET(GET_GROUPS_PATH)
    suspend fun getGroups(
        @Query(DEPARTMENT_ID_PATH_KEY) departmentId: Int,
        @Query(FACULTY_ID_PATH_KEY) facultyId: Int,
        @Query(COURSE_PATH_KEY) course: Int,
        @Query(LANGUAGE_PATH_KEY) language: String? = null
    ): ApiResult<GroupsDto, ErrorWrapperDto>

    @GET(GET_TEACHERS_PATH)
    suspend fun getTeachers(
        @Query(TEACHER_ID_PATH_KEY) teacherId: String? = null,
        @Query(SORT_PATH_KEY) sort: String? = null,
        @Query(LANGUAGE_PATH_KEY) language: String? = null
    ): ApiResult<TeachersDto, ErrorWrapperDto>

    @GET(GET_GROUP_SCHEDULE_PATH)
    suspend fun getGroupSchedule(
        @Query(GROUP_ID_PATH_KEY) groupId: String,
        @Query(DATE_START_PATH_KEY) dateStart: LocalDate? = null,
        @Query(DATE_END_PATH_KEY) dateEnd: LocalDate? = null,
        @Query(LANGUAGE_PATH_KEY) language: String? = null
    ): ApiResult<ScheduleDto, ErrorWrapperDto>

    @GET(GET_TEACHER_SCHEDULE_PATH)
    suspend fun getTeacherSchedule(
        @Query(TEACHER_ID_PATH_KEY) teacherId: String,
        @Query(DATE_START_PATH_KEY) dateStart: LocalDate? = null,
        @Query(DATE_END_PATH_KEY) dateEnd: LocalDate? = null,
        @Query(LANGUAGE_PATH_KEY) language: String? = null
    ): ApiResult<ScheduleDto, ErrorWrapperDto>


}