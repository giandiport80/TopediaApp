package com.giandiport80.topediaapp.ui.adminpanel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.databinding.ActivityAdminPanelBinding
import com.giandiport80.topediaapp.ui.category.ListCategoryAdminActivity

class AdminPanelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminPanelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Admin Panel"

        setData()
        setupListener()
    }

    private fun setupListener() {
        binding.apply {
            btnCategory.setOnClickListener {
                val intent = Intent(this@AdminPanelActivity, ListCategoryAdminActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setData() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}