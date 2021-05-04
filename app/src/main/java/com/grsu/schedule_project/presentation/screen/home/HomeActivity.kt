package com.grsu.schedule_project.presentation.screen.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.ScheduleNavigator
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.databinding.ActivityHomeBinding
import com.grsu.schedule_project.presentation.common.OnGroupClickListener
import com.grsu.schedule_project.presentation.common.OnTabChanged
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeActivity : AppCompatActivity(R.layout.activity_home), OnGroupClickListener, OnTabChanged {

    companion object {
        fun getIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    private val viewBinding: ActivityHomeBinding by viewBinding(R.id.container)

    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator: Navigator by lazy {
        ScheduleNavigator(this, R.id.fragmentContainer)
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    private val utils: Utils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = getViewModel()

        bottomNavigationView = viewBinding.bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.schedulePage -> {
                    homeViewModel.switchTab(
                        HomeScreens.scheduleContainerScreen(),
                        it.title.toString()
                    )
                    true
                }
                R.id.bookmarksPage -> {
                    homeViewModel.switchTab(HomeScreens.bookmarksScreen(), it.title.toString())
                    true
                }
                R.id.settingsPage -> {
                    homeViewModel.switchTab(HomeScreens.settingsScreen(), it.title.toString())
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.schedulePage
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        homeViewModel.onBackPressed()
    }

    override fun onGroupClick(groupId: String?, groupTitle: String?) {
        homeViewModel.onGroupClick(
            utils.getStringById(R.string.menu_schedule_title),
            groupId,
            groupTitle
        )
    }

    override fun onTabChange(tabId: Int) {
        bottomNavigationView.selectedItemId = tabId
    }
}