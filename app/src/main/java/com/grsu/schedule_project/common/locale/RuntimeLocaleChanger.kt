package com.grsu.schedule_project.common.locale

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.di.PRIVATE_STORAGE_NAME
import java.util.*

class RuntimeLocaleChanger(private val context: Context) {

    val preferences: SharedPreferences =
        context.getSharedPreferences(PRIVATE_STORAGE_NAME, Context.MODE_PRIVATE)
    private var appLanguage by preferences.string(APP_LANGUAGE_KEY)

    fun wrapContext(): Context {
        if (appLanguage.isNullOrEmpty()) {
            appLanguage = "en_GB"
        }

        val savedLocale = appLanguage?.substringBefore('_')?.let { Locale(it) }
            ?: return context
        Locale.setDefault(savedLocale)

        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)

        return context.createConfigurationContext(newConfig)
    }

    @Suppress("DEPRECATION")
    fun overrideLocale() {
        if (appLanguage.isNullOrEmpty()) {
            appLanguage = "en_GB"
        }

        val savedLocale = appLanguage?.substringBefore('_')?.let { Locale(it) }
            ?: return
        Locale.setDefault(savedLocale)

        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)

        context.resources.updateConfiguration(newConfig, context.resources.displayMetrics)
        if (context != context.applicationContext) {
            context.applicationContext.resources.run {
                updateConfiguration(newConfig, displayMetrics)
            }
        }
    }
}