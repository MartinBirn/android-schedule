package com.grsu.schedule_project.common.utils

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.grsu.schedule_project.R
import java.text.SimpleDateFormat
import java.util.*

private const val UTC = "UTC"

const val DATE_API_REQUEST_PATTERN = "dd.MM.yyyy"
const val DATE_API_RESPONSE_PATTERN = "yyyy-MM-dd"
const val DATE_SCHEDULE_VIEW_PATTERN = "dd.MM"

class DateUtils(private val context: Context) {

    private val defaultTimeZone = TimeZone.getTimeZone(UTC)
    private val defaultLocale =
        ConfigurationCompat.getLocales(context.resources.configuration).get(0)

    fun getDateFormatInstance(
        pattern: String,
        locale: Locale = defaultLocale,
        timeZone: TimeZone = defaultTimeZone
    ) = SimpleDateFormat(pattern, locale).apply { this.timeZone = timeZone }

    fun getTodayInUtcMillis() = MaterialDatePicker.todayInUtcMilliseconds()

    fun getCalendarInstance(timeZone: TimeZone = defaultTimeZone): Calendar =
        Calendar.getInstance().apply { this.timeZone = timeZone }

    fun getNowInMillis(timeZone: TimeZone = defaultTimeZone) =
        getCalendarInstance(timeZone).timeInMillis

    fun getNowOffsetInMillis(offset: Int, timeZone: TimeZone = defaultTimeZone) =
        getCalendarInstance(timeZone).apply {
            add(Calendar.DATE, offset)
        }.timeInMillis

    fun getTodayOffsetInMillis(offset: Int, timeZone: TimeZone = defaultTimeZone) =
        Calendar.getInstance(timeZone).apply {
            timeInMillis = getTodayInUtcMillis()
            add(Calendar.DATE, offset)
        }.timeInMillis

    fun getDayOfWeek(dayOfWeekNumber: Int): String? =
        context.resources.getStringArray(R.array.title_day_of_week).getOrNull(dayOfWeekNumber)
}