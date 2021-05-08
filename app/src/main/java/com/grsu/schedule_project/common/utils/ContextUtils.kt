package com.grsu.schedule_project.common.utils

import android.content.Context


class ContextManager(private val androidContext: Context) {

    //set activity context in onResume stage
    private var activityContext: Context? = null
    var context: Context
        get() = activityContext ?: androidContext
        set(value) {
            activityContext = value
        }

    //clear activity context in onPause stage
    fun clearContext() {
        activityContext = null
    }
}