package com.grsu.schedule_project.data.source.schedule.local

import com.grsu.schedule_project.data.database.dao.DayDao
import com.grsu.schedule_project.data.database.dao.LessonDao
import com.grsu.schedule_project.data.model.dbo.DayDbo
import com.grsu.schedule_project.data.model.dbo.LessonDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.source.schedule.ScheduleDataSource

class ScheduleLocalDataSource(private val dayDao: DayDao, private val lessonDao: LessonDao) :
    ScheduleDataSource {

    override suspend fun getGroupSchedule(
        groupId: String,
        dateStart: String?,
        dateEnd: String?,
        language: String?
    ): RepoResult<List<DayDbo>, *> {
        val daysWithLessonsDbo = dayDao.getDaysWithLessons()
        val days = daysWithLessonsDbo.map { it.dayDbo.apply { this.lessons = it.lessons } }
        return days.let(::Success)
    }

    suspend fun insert(vararg days: DayDbo) {
        dayDao.insert(*days)
    }

    suspend fun insert(vararg lessons: LessonDbo) {
        lessonDao.insert(*lessons)
    }

    suspend fun insertAndDeletePrevious(vararg days: DayDbo) {
        dayDao.insertAndDeletePrevious(*days)
    }

    suspend fun insertAndDeletePrevious(vararg lessons: LessonDbo) {
        lessonDao.insertAndDeletePrevious(*lessons)
    }

    suspend fun deleteAll() {
        //by cascade will delete all lessons
        dayDao.deleteAll()
    }
}