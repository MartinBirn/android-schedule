package com.grsu.schedule_project.presentation.screen.home.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.APP_THEME_DARK_VALUE
import com.grsu.schedule_project.common.APP_THEME_KEY
import com.grsu.schedule_project.common.APP_THEME_LIGHT_VALUE
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.databinding.FragmentSettingsBinding
import com.grsu.schedule_project.di.PRIVATE_STORAGE
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class SettingsFragment : Fragment(R.layout.fragment_settings), BackButtonListener {

    companion object {
        fun getNewInstance() = SettingsFragment()
    }

    private val viewBinding: FragmentSettingsBinding by viewBinding()

    private val settingsViewModel: SettingsViewModel by viewModel()

    private val utils: Utils by inject()
    private val preferences: SharedPreferences by inject(named(PRIVATE_STORAGE))
    private var appTheme by preferences.string(APP_THEME_KEY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.title = utils.getStringById(R.string.toolbar_settings_title)
        viewBinding.themeItem.description = when (appTheme) {
            APP_THEME_LIGHT_VALUE -> utils.getStringById(R.string.app_theme_light)
            APP_THEME_DARK_VALUE -> utils.getStringById(R.string.app_theme_dark)
            else -> utils.getStringById(R.string.app_theme_light)
        }

        viewBinding.languageItem.setOnClickListener {
            settingsViewModel.navigateTo(HomeScreens.languagesScreen())
        }
        viewBinding.themeItem.setOnClickListener {
            settingsViewModel.navigateTo(HomeScreens.themesScreen())
        }
    }

    override fun onBackPressed(): Boolean {
        settingsViewModel.onBackPressed()
        return true
    }
}