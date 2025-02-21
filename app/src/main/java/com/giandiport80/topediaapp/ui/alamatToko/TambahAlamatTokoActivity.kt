package com.giandiport80.topediaapp.ui.alamatToko

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.databinding.ActivityTambahAlamatTokoBinding

class TambahAlamatTokoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahAlamatTokoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambahAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.visibility = View.VISIBLE
            lyToolbar.btnSimpan.setOnClickListener {
                simpanAlamat()
            }
        }
    }

    private fun simpanAlamat() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}