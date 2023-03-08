package com.example.recipes.data.sharedpref

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(

    private val sharedPreferences: SharedPreferences
) {
    fun setDarkThemeEnable(isEnable: Boolean) {
        sharedPreferences.edit().putBoolean(IS_DARK_THEME_ENABLE, isEnable).apply()

    }

    companion object {
        private const val IS_DARK_THEME_ENABLE = "IS_DARK_THEME_ENABLE"

    }

}