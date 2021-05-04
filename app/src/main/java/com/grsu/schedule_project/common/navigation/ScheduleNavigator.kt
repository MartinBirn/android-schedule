package com.grsu.schedule_project.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.commands.*
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.common.OnGroupClickListener

class ScheduleNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager
) : AppNavigator(activity, containerId, fragmentManager) {

    override fun applyCommand(command: Command) {
        when (command) {
            is SwitchTabCommand -> switchTab(command)
            is BackInActivity -> backInActivity()
            is BackInContainer -> backInContainer(command)
            is OpenScheduleInActivity -> openScheduleInActivity(command)
            is OpenSchedule -> openSchedule(command)
            else -> super.applyCommand(command)
        }
    }

    private fun switchTab(command: SwitchTabCommand) {
        var currentFragment: Fragment? = null
        val fragments = fragmentManager.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fragmentManager.findFragmentByTag(command.title)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) {
            currentFragment.childFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            return
        }
        fragmentManager.commitNow {
            if (newFragment == null) {
                add(
                    R.id.fragmentContainer,
                    command.screen.createFragment(fragmentManager.fragmentFactory), command.title
                )
            }
            if (currentFragment != null) {
                hide(currentFragment)
            }
            if (newFragment != null) {
                show(newFragment)
            }
        }
    }

    private fun backInActivity() {
        val fm = activity.supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            back()
        }
    }

    private fun backInContainer(command: BackInContainer) {
        val fragment = fragmentManager.findFragmentById(command.id)
        if (fragment != null && fragment is BackButtonListener) {
            (fragment as BackButtonListener).onBackPressed()
        } else {
            back()
        }
    }

    private fun openScheduleInActivity(command: OpenScheduleInActivity) {
        val fragment = fragmentManager.findFragmentByTag(command.containerTag)
        (fragment as? OnGroupClickListener)?.onGroupClick(command.groupId, command.groupTitle)
    }

    private fun openSchedule(command: OpenSchedule) {
        (activity as? OnGroupClickListener)?.onGroupClick(command.groupId, command.groupTitle)
    }
}