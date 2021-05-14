package com.grsu.schedule_project.presentation.screen.home.schedulecontainer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.ScheduleNavigator
import com.grsu.schedule_project.di.SCHEDULE_CONTAINER
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.common.OnGroupClickListener
import com.grsu.schedule_project.presentation.common.OnTabChanged
import com.grsu.schedule_project.presentation.common.RecreateActionListener
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class ScheduleContainerFragment : Fragment(R.layout.container_schedule), BackButtonListener,
    OnGroupClickListener, RecreateActionListener {

    companion object {
        fun getNewInstance() = ScheduleContainerFragment()
    }

    private val navigatorHolder: NavigatorHolder by inject(qualifier = named(SCHEDULE_CONTAINER))
    private val navigator: Navigator by lazy {
        ScheduleNavigator(requireActivity(), R.id.scheduleContainer, childFragmentManager)
    }

    private val scheduleContainerViewModel: ScheduleContainerViewModel by viewModel()

    private var onBackStackListener: FragmentManager.OnBackStackChangedListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.scheduleContainer) == null) {
            scheduleContainerViewModel.replaceScreen(HomeScreens.departmentScreen())
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun recreate() {
        childFragmentManager.fragments.map {
            (it as? RecreateActionListener)?.recreate()
        }
    }

    override fun onBackPressed(): Boolean {
        scheduleContainerViewModel.onBackPressed()
        return true
    }

    override fun onGroupClick(groupId: String?, groupTitle: String?) {
        childFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        //for a seamless transition
        onBackStackListener = FragmentManager.OnBackStackChangedListener {
            (activity as? OnTabChanged)?.onTabChange(R.id.schedulePage)
            childFragmentManager.removeOnBackStackChangedListener(onBackStackListener!!)
        }.also {
            childFragmentManager.addOnBackStackChangedListener(it)
        }

        scheduleContainerViewModel.navigateTo(
            HomeScreens.scheduleScreen(groupId, groupTitle)
        )
    }
}