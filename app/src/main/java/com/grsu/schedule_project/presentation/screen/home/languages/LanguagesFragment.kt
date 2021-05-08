package com.grsu.schedule_project.presentation.screen.home.languages

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.APP_LANGUAGE_BY_VALUE
import com.grsu.schedule_project.common.APP_LANGUAGE_EN_VALUE
import com.grsu.schedule_project.common.APP_LANGUAGE_RU_VALUE
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.databinding.FragmentLanguagesBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LanguagesFragment : Fragment(R.layout.fragment_languages), BackButtonListener {

    companion object {
        fun getNewInstance() = LanguagesFragment()
    }

    private val viewBinding: FragmentLanguagesBinding by viewBinding()

    private val languagesViewModel: LanguagesViewModel by viewModel()

    private val utils: Utils by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.toolbar.title = utils.getStringById(R.string.toolbar_languages_title)
        viewBinding.toolbar.setNavigationOnClickListener {
            languagesViewModel.onBackPressed()
        }

        subscribeUi()
    }

    private fun subscribeUi() {
        viewBinding.enLanguageItem.setOnClickListener {
            languagesViewModel.changeAppLanguage(APP_LANGUAGE_EN_VALUE)
        }
        viewBinding.ruLanguageItem.setOnClickListener {
            languagesViewModel.changeAppLanguage(APP_LANGUAGE_RU_VALUE)
        }
        viewBinding.beLanguageItem.setOnClickListener {
            languagesViewModel.changeAppLanguage(APP_LANGUAGE_BY_VALUE)
        }
    }

    override fun onBackPressed(): Boolean {
        languagesViewModel.onBackPressed()
        return true
    }
}