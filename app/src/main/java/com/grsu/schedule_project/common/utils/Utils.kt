package com.grsu.schedule_project.common.utils

import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import java.util.*

class Utils(private val contextManager: ContextManager) {

    fun getStringById(resId: Int) = contextManager.context.getString(resId)

    fun getDimensionById(resId: Int) = contextManager.context.resources.getDimension(resId)

    fun getColorById(resId: Int) = ContextCompat.getColor(contextManager.context, resId)

    fun getDrawableById(resId: Int) = ContextCompat.getDrawable(contextManager.context, resId)

    fun getCurrentLocale(): Locale =
        ConfigurationCompat.getLocales(contextManager.context.resources.configuration).get(0)
}