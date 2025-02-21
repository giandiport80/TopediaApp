package com.giandiport80.topediaapp.ui.alamatToko

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.core.data.source.model.AlamatToko
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityTambahAlamatTokoBinding
import com.inyongtisto.myhelper.extension.showErrorDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class TambahAlamatTokoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()

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
        val requestData = AlamatToko()
        viewModel.createAlamatToko(requestData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {


                }

                State.ERROR -> {
                    it.message?.let { it1 -> showErrorDialog(it1) }
                }

                State.LOADING -> {

                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}