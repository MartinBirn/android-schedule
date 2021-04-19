package com.grsu.schedule_project.core.preferences.util

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private inline fun <T> SharedPreferences.delegate(
    key: String?,
    defaultValue: T,
    crossinline getter: SharedPreferences.(String, T) -> T,
    crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T =
            getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
            edit().setter(key ?: property.name, value).apply()
    }
}

fun SharedPreferences.int(key: String, defaultValue: Int = 0) =
    delegate(key, defaultValue, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

fun SharedPreferences.long(key: String, defaultValue: Long = 0) =
    delegate(key, defaultValue, SharedPreferences::getLong, SharedPreferences.Editor::putLong)

fun SharedPreferences.string(key: String, defaultValue: String = "") =
    delegate(key, defaultValue, SharedPreferences::getString, SharedPreferences.Editor::putString)

fun SharedPreferences.boolean(key: String, defaultValue: Boolean = false) =
    delegate(key, defaultValue, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)