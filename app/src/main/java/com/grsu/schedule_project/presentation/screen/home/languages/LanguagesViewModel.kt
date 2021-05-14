package com.grsu.schedule_project.presentation.screen.home.languages

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.TAB_ID_KEY
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.data.source.department.DepartmentRepository
import com.grsu.schedule_project.data.source.faculty.FacultyRepository
import kotlinx.coroutines.launch

class LanguagesViewModel(
    private val router: ScheduleRouter,
    private val preferences: SharedPreferences,
    private val departmentRepository: DepartmentRepository,
    private val facultyRepository: FacultyRepository
) : ViewModel() {

    private var appLanguage by preferences.string(APP_LANGUAGE_KEY)

    fun changeAppLanguage(language: String) {
        appLanguage = language

        viewModelScope.launch {
            departmentRepository.deleteDepartments()
            facultyRepository.deleteFaculties()
        }

        router.restartActivity(TAB_ID_KEY to R.id.settingsPage.toString())
    }

    fun onBackPressed() {
        router.exit()
    }
}