package com.grsu.schedule_project.presentation.screen.home.themes

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.*
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.common.preferences.util.int
import com.grsu.schedule_project.common.preferences.util.string

class ThemesViewModel(
    private val router: ScheduleRouter,
    private val preferences: SharedPreferences
) : ViewModel() {

    private var appTheme by preferences.string(APP_THEME_KEY)
    private var appThemeId by preferences.int(APP_THEME_ID_KEY)

    fun changeAppTheme(theme: String) {
        appThemeId = when (theme) {
            APP_THEME_LIGHT_VALUE -> R.style.AppThemeLight
            APP_THEME_DARK_VALUE -> R.style.AppThemeDark
            else -> R.style.AppThemeLight
        }
        if (appTheme == theme) {
            router.exit()
            return
        }
        appTheme = theme

        router.restartActivity(TAB_ID_KEY to R.id.settingsPage.toString())
    }

    fun onBackPressed() {
        router.exit()
    }
}