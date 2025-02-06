package com.giandiport80.topediaapp.util

object Helper {
    fun getInitialName(name: String?): String {
        if (name.isNullOrBlank()) return ""

        val words = name.trim().split(" ").filter { it.isNotBlank() }
        val initials = words.take(2).joinToString("") { it.take(1) }

        return initials.uppercase()
    }
}
