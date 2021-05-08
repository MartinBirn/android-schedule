package com.grsu.schedule_project.presentation.screen.home.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.databinding.FragmentSettingsBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment(R.layout.fragment_settings), BackButtonListener {

    companion object {
        fun getNewInstance() = SettingsFragment()
    }

    private val viewBinding: FragmentSettingsBinding by viewBinding()

    private val settingsViewModel: SettingsViewModel by viewModel()

    private val utils: Utils by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.title = utils.getStringById(R.string.toolbar_settings_title)
        viewBinding.languageItem.setOnClickListener {
            settingsViewModel.navigateTo(HomeScreens.languagesScreen())
        }
    }

    override fun onBackPressed(): Boolean {
        settingsViewModel.onBackPressed()
        return true
    }
}