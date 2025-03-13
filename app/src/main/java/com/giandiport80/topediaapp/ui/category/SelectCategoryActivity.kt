package com.giandiport80.topediaapp.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityListCategoryAdminBinding
import com.giandiport80.topediaapp.ui.category.adapter.CategoryAdminAdapter
import com.giandiport80.topediaapp.ui.category.adapter.SelectCategoryAdapter
import com.giandiport80.topediaapp.util.defaultError
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.showConfirmDialog
import com.inyongtisto.myhelper.extension.showErrorDialog
import com.inyongtisto.myhelper.extension.toJson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectCategoryActivity() : CustomeActivity() {
    private lateinit var binding: ActivityListCategoryAdminBinding
    private val viewModel: CategoryViewModel by viewModel()
    private var adapter = SelectCategoryAdapter(
        onClick = {
            val intent = Intent()
            intent.putExtra("extra", it.toJson())
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
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

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun setupUI() {
        binding.apply {
            lyToolbar.btnTambah.visibility = View.VISIBLE
            lyToolbar.btnTambah.setOnClickListener {
                val intent =
                    Intent(this@SelectCategoryActivity, CreateCategoryActivity::class.java)
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