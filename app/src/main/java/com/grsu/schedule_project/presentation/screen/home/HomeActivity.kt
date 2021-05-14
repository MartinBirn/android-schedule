package com.grsu.schedule_project.presentation.screen.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.APP_THEME_ID_KEY
import com.grsu.schedule_project.common.TAB_ID_KEY
import com.grsu.schedule_project.common.locale.RuntimeLocaleChanger
import com.grsu.schedule_project.common.navigation.ScheduleNavigator
import com.grsu.schedule_project.common.preferences.util.int
import com.grsu.schedule_project.common.utils.ContextManager
import com.grsu.schedule_project.databinding.ActivityHomeBinding
import com.grsu.schedule_project.di.PRIVATE_STORAGE
import com.grsu.schedule_project.presentation.common.OnGroupClickListener
import com.grsu.schedule_project.presentation.common.OnTabChanged
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.qualifier.named

private const val SCHEDULE_TAB_TAG = "schedule_tab_tag"
private const val BOOKMARKS_TAB_TAG = "bookmarks_tab_tag"
private const val SETTINGS_TAB_TAG = "settings_tab_tag"

private const val RECREATED_KEY = "recreated_key"

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

    private val contextManager: ContextManager by inject()
    private val preferences: SharedPreferences by inject(named(PRIVATE_STORAGE))
    private val appThemeId by preferences.int(APP_THEME_ID_KEY)

    private var recreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val themeId: Int = appThemeId
        if (themeId != 0) setTheme(themeId)
        super.onCreate(savedInstanceState)
        homeViewModel = getViewModel()

        bottomNavigationView = viewBinding.bottomNavigation
        bottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.schedulePage -> {
                    homeViewModel.switchTab(
                        HomeScreens.scheduleContainerScreen(),
                        SCHEDULE_TAB_TAG
                    )
                    true
                }
                R.id.bookmarksPage -> {
                    homeViewModel.switchTab(
                        HomeScreens.bookmarksScreen(),
                        BOOKMARKS_TAB_TAG
                    )
                    true
                }
                R.id.settingsPage -> {
                    homeViewModel.switchTab(
                        HomeScreens.settingsContainerScreen(),
                        SETTINGS_TAB_TAG
                    )
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState?.getBoolean(RECREATED_KEY) == true) return

        val argumentTabId: String? = intent.getStringExtra(TAB_ID_KEY)
        bottomNavigationView.selectedItemId = argumentTabId?.toIntOrNull() ?: R.id.schedulePage
    }

    override fun onResume() {
        super.onResume()
        contextManager.context = this
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        contextManager.context = this
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        homeViewModel.onBackPressed()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { RuntimeLocaleChanger(it).wrapContext() })
    }

    override fun recreate() {
        super.recreate()
        recreated = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(RECREATED_KEY, recreated)
    }

    override fun onGroupClick(groupId: String?, groupTitle: String?) {
        homeViewModel.onGroupClick(
            SCHEDULE_TAB_TAG,
            groupId,
            groupTitle
        )
    }

    override fun onTabChange(tabId: Int) {
        bottomNavigationView.selectedItemId = tabId
    }
}