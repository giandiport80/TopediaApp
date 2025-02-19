package com.giandiport80.topediaapp.ui.alamatToko

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityListAlamatTokoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAlamatTokoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Daftar Alamat"

        mainButton()
        getData()
    }

    private fun getData() {
        viewModel.getAlamatToko().observe(this) {
            when (it.state) {
                State.SUCCESS -> {

                }

                State.ERROR -> {

                }

                State.LOADING -> {

                }
            }
        }
    }

    private fun mainButton() {

    }
}