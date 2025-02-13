package com.giandiport80.topediaapp.util

import java.text.NumberFormat
import java.util.Locale

object Helper {
    fun getInitialName(name: String?): String {
        if (name.isNullOrBlank()) return ""

        val words = name.trim().split(" ").filter { it.isNotBlank() }
        val initials = words.take(2).joinToString("") { it.take(1) }

        return initials.uppercase()
    }

    fun toRupiah(number: Int, hideCurrency: Boolean = false): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        var value = format.format(number).replace(",00", "")
        if (hideCurrency) value = value.replace("Rp", "")
        return value
    }
}
