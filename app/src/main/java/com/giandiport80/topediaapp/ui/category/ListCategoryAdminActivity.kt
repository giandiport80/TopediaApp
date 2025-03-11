package com.giandiport80.topediaapp.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityListCategoryAdminBinding
import com.giandiport80.topediaapp.ui.category.adapter.CategoryAdminAdapter
import com.giandiport80.topediaapp.util.defaultError
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.showConfirmDialog
import com.inyongtisto.myhelper.extension.showErrorDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCategoryAdminActivity() : CustomeActivity() {
    private lateinit var binding: ActivityListCategoryAdminBinding
    private val viewModel: CategoryViewModel by viewModel()
    private var adapter = CategoryAdminAdapter(
        onDelete = { item, position ->
            confirmDelete(item, position)
        },
        onClick = {

        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListCategoryAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Daftar Category"

        mainButton()
        getData()
        setupAdapter()
        setupUI()
    }

    private fun onDelete(item: Category, position: Int) {
        viewModel.deleteCategory(item.id).observe(this) {
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

    private fun confirmDelete(item: Category, position: Int) {
        showConfirmDialog(
            "Delete Category",
            "Apakah kamu yakin ingin menghapus category ini?",
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
                    Intent(this@ListCategoryAdminActivity, CreateCategoryActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.getCategory().observe(this) {
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