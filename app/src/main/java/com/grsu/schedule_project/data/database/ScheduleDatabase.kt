package com.grsu.schedule_project.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grsu.schedule_project.data.database.dao.*
import com.grsu.schedule_project.data.model.dbo.*

@Database(
    entities = [TeacherDbo::class, FacultyDbo::class, DepartmentDbo::class, GroupDbo::class,
        DayDbo::class, LessonDbo::class, LessonTeacherDbo::class],
    version = 1,
    exportSchema = false
)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun teacherDao(): TeacherDao

    abstract fun facultyDao(): FacultyDao

    abstract fun departmentDao(): DepartmentDao

    abstract fun groupDao(): GroupDao

    abstract fun dayDao(): DayDao

    abstract fun lessonDao(): LessonDao

    abstract fun lessonTeacherDao(): LessonTeacherDao
}