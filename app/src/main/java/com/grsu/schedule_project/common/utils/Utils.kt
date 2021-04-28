package com.grsu.schedule_project.common.utils

import android.content.Context
import androidx.core.content.ContextCompat

class Utils(private val context: Context) {

    fun getStringById(resId: Int) = context.getString(resId)

    fun getDimensionById(resId: Int) = context.resources.getDimension(resId)

    fun getColorById(resId: Int) = ContextCompat.getColor(context, resId)
}