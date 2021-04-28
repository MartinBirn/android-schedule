package com.grsu.schedule_project.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.commands.BackInActivity
import com.grsu.schedule_project.common.navigation.commands.BackInContainer
import com.grsu.schedule_project.common.navigation.commands.SwitchTabCommand
import com.grsu.schedule_project.presentation.common.BackButtonListener

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
            else -> super.applyCommand(command)
        }
    }

    private fun switchTab(command: SwitchTabCommand) {
        val fm = fragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fm.findFragmentByTag(command.title)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return
        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.fragmentContainer,
                command.screen.createFragment(fm.fragmentFactory), command.title
            )
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
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
}