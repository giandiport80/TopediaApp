package com.giandiport80.topediaapp.ui.product

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.databinding.ActivityDetailProductBinding

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainButton()
    }

    private fun mainButton() {
        binding.btnBack.setOnClickListener {

        }
    }

}