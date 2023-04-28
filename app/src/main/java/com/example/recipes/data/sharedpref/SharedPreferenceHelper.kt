package com.example.recipes.data.sharedpref

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(

    private val sharedPreferences: SharedPreferences
) {

    fun isDarkThemeEnable(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME_KEY, false)
    }

    fun setDarkThemeEnable(isEnable: Boolean) {
        sharedPreferences.edit().putBoolean(DARK_THEME_KEY, isEnable).apply()
    }

    companion object {
        private const val DARK_THEME_KEY = "DARK_THEME_KEY"

    }
}