package com.giandiport80.topediaapp.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Prefs(activity: Activity) {
    companion object {
        private const val LOGIN = "login"
    }

    private var sp: SharedPreferences? = null

    init {
        sp = activity.getSharedPreferences("app", Context.MODE_PRIVATE)
    }

    fun isLogin(value: Boolean) {
        sp!!.edit().putBoolean(LOGIN, true).apply()
    }

    fun getIsLogin(): Boolean {
        return sp!!.getBoolean(LOGIN, false)
    }
}