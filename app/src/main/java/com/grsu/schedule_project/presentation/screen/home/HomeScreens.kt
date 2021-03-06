package com.grsu.schedule_project.presentation.screen.home

import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grsu.schedule_project.presentation.screen.home.bookmarks.BookmarksFragment
import com.grsu.schedule_project.presentation.screen.home.courses.CoursesFragment
import com.grsu.schedule_project.presentation.screen.home.departments.DepartmentsFragment
import com.grsu.schedule_project.presentation.screen.home.faculties.FacultiesFragment
import com.grsu.schedule_project.presentation.screen.home.groups.GroupsFragment
import com.grsu.schedule_project.presentation.screen.home.languages.LanguagesFragment
import com.grsu.schedule_project.presentation.screen.home.schedule.ScheduleFragment
import com.grsu.schedule_project.presentation.screen.home.schedulecontainer.ScheduleContainerFragment
import com.grsu.schedule_project.presentation.screen.home.settings.SettingsFragment
import com.grsu.schedule_project.presentation.screen.home.settingscontainer.SettingsContainerFragment
import com.grsu.schedule_project.presentation.screen.home.teacher.TeacherFragment
import com.grsu.schedule_project.presentation.screen.home.themes.ThemesFragment

object HomeScreens {

    fun homeScreen() = ActivityScreen { HomeActivity.getIntent(it) }

    fun scheduleContainerScreen() = FragmentScreen { ScheduleContainerFragment.getNewInstance() }

    fun departmentScreen() = FragmentScreen { DepartmentsFragment.getNewInstance() }

    fun facultyScreen(departmentId: String?) =
        FragmentScreen { FacultiesFragment.getNewInstance(departmentId) }

    fun courseScreen(departmentId: String?, facultyId: String?) =
        FragmentScreen { CoursesFragment.getNewInstance(departmentId, facultyId) }

    fun groupScreen(departmentId: String?, facultyId: String?, courseId: String?) =
        FragmentScreen { GroupsFragment.getNewInstance(departmentId, facultyId, courseId) }

    fun scheduleScreen(groupId: String?, groupTitle: String?) =
        FragmentScreen { ScheduleFragment.getNewInstance(groupId, groupTitle) }

    fun teacherScreen(teacherId: String?) =
        FragmentScreen { TeacherFragment.getNewInstance(teacherId) }

    fun bookmarksScreen() = FragmentScreen { BookmarksFragment.getNewInstance() }

    fun settingsContainerScreen() = FragmentScreen { SettingsContainerFragment.getNewInstance() }

    fun settingsScreen() = FragmentScreen { SettingsFragment.getNewInstance() }

    fun languagesScreen() = FragmentScreen { LanguagesFragment.getNewInstance() }

    fun themesScreen() = FragmentScreen { ThemesFragment.getNewInstance() }
}