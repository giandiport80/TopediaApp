package com.giandiport80.topediaapp.ui.slider

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.giandiport80.topediaapp.core.data.source.model.Slider
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityListSliderAdminBinding
import com.giandiport80.topediaapp.ui.slider.adapter.SliderAdminAdapter
import com.giandiport80.topediaapp.util.defaultError
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.dismissLoading
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.showConfirmDialog
import com.inyongtisto.myhelper.extension.showErrorDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListSliderAdminActivity() : CustomeActivity() {
    private lateinit var binding: ActivityListSliderAdminBinding
    private val viewModel: SliderViewModel by viewModel()
    private var adapter = SliderAdminAdapter(
        onDelete = { item, position ->
            confirmDelete(item, position)
        },
        onClick = {
            intentActivity(CreateSliderActivity::class.java, it)
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListSliderAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Daftar Slider"

        mainButton()
        getData()
        setupAdapter()
        setupUI()
    }

    private fun onDelete(item: Slider, position: Int) {
        viewModel.delete(item.id).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    adapter.removeAt(position)
                    Toast.makeText(this, "Alamat berhasil dihapus", Toast.LENGTH_SHORT).show()
                }

                State.ERROR -> {
                    progress.dismiss()
                    showErrorDialog(it.message.defaultError())
                }

                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    private fun confirmDelete(item: Slider, position: Int) {
        showConfirmDialog(
            "Delete Slider",
            "Apakah kamu yakin ingin menghapus Slider ini?",
            "Delete"
        ) {
            onDelete(item, position)
        }
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
                    Intent(this@ListSliderAdminActivity, CreateSliderActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.get().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.swipeRefresh.dismissLoading()
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
        binding.swipeRefresh.setOnRefreshListener {
            getData()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}