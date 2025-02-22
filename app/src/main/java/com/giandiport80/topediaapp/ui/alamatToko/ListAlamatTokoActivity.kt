package com.giandiport80.topediaapp.ui.alamatToko

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityListAlamatTokoBinding
import com.giandiport80.topediaapp.ui.alamatToko.adapter.AlamatTokoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAlamatTokoActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityListAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private var adapter = AlamatTokoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Daftar Alamat"

        mainButton()
        getData()
        setupAdapter()
        setupUI()
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun setupUI() {
        binding.apply {
            lyToolbar.btnTambah.visibility = View.VISIBLE
            lyToolbar.btnTambah.setOnClickListener {
                val intent =
                    Intent(this@ListAlamatTokoActivity, TambahAlamatTokoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.getAlamatToko().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.tvError.visibility = View.GONE

                    val data = it.data ?: emptyList()
                    adapter.addItems(data)

                    if (data.isEmpty()) {
                        binding.tvError.visibility = View.VISIBLE
                    }
                }

                State.ERROR -> {
                    binding.tvError.visibility = View.VISIBLE
                }

                State.LOADING -> {

                }
            }
        }
    }

    private fun mainButton() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}