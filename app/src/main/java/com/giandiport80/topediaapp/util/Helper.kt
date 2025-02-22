package com.giandiport80.topediaapp.util

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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

    fun <T> setupSpinner(
        context: Context,
        spinner: Spinner,
        items: List<T>,
        onItemSelected: ((T) -> Unit)? = null
    ) {
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                onItemSelected?.invoke(items[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Bisa dikosongkan jika tidak ada aksi
            }
        }
    }
}
