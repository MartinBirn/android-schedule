package com.grsu.schedule_project.presentation.screen.home.schedulecontainer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.grsu.schedule_project.R
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import org.koin.androidx.viewmodel.ext.android.viewModel


class ScheduleContainerFragment : Fragment(R.layout.schedule_container) {

    companion object {
        fun getNewInstance() = ScheduleContainerFragment()
    }

    private val scheduleContainerViewModel: ScheduleContainerViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.scheduleContainer) == null) {
            scheduleContainerViewModel.replaceScreen(HomeScreens.departmentScreen())
        }
    }
}