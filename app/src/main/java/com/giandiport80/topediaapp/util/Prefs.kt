package com.giandiport80.topediaapp.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.chibatching.kotpref.KotprefModel

object Prefs : KotprefModel() {
    public var isLogin by booleanPref(false)
}