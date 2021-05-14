package com.grsu.schedule_project.presentation.screen.home.themes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.APP_THEME_DARK_VALUE
import com.grsu.schedule_project.common.APP_THEME_LIGHT_VALUE
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.databinding.FragmentThemesBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThemesFragment : Fragment(R.layout.fragment_themes), BackButtonListener {

    companion object {
        fun getNewInstance() = ThemesFragment()
    }

    private val viewBinding: FragmentThemesBinding by viewBinding()

    private val themesViewModel: ThemesViewModel by viewModel()

    private val utils: Utils by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.toolbar.title = utils.getStringById(R.string.toolbar_themes_title)
        viewBinding.toolbar.setNavigationOnClickListener {
            themesViewModel.onBackPressed()
        }

        subscribeUi()
    }

    private fun subscribeUi() {
        viewBinding.lightThemeItem.setOnClickListener {
            themesViewModel.changeAppTheme(APP_THEME_LIGHT_VALUE)
        }
        viewBinding.darkThemeItem.setOnClickListener {
            themesViewModel.changeAppTheme(APP_THEME_DARK_VALUE)
        }
    }

    override fun onBackPressed(): Boolean {
        themesViewModel.onBackPressed()
        return true
    }
}