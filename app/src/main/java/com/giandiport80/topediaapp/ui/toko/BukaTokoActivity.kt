package com.giandiport80.topediaapp.ui.toko

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.databinding.ActivityBukaTokoBinding
import com.inyongtisto.myhelper.extension.setToolbar

class BukaTokoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBukaTokoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        mainButton()
    }

    private fun mainButton() {
        binding.btnBukToko.setOnClickListener {
            val intent = Intent(this, TokoSayaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}