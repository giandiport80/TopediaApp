package com.giandiport80.topediaapp.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.chibatching.kotpref.KotprefModel
import com.giandiport80.topediaapp.core.data.source.model.User
import com.google.gson.Gson
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object Prefs : KotprefModel() {
    var isLogin by booleanPref(false)
    private var user by stringPref()

    fun setUser(userData: User?) {
        user = Gson().toJson(userData)
    }

    fun getUser(): User? {
        return if (user.isNotEmpty()) {
            Gson().fromJson(user, User::class.java)
        } else {
            null
        }
    }
}

fun getTokoId(): Int? = Prefs.getUser()?.toko?.id