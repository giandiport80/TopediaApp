package com.giandiport80.topediaapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.databinding.ActivityLoginBinding
import com.giandiport80.topediaapp.util.Prefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModel() // di

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setData()
    }

    private fun setData() {
        viewModel.text.observe(this) {
            binding.editTextEmail.setText(it)
        }

        binding.btnLogin.setOnClickListener {
            viewModel.ubahData("oke")
        }
    }

    private fun testing() {
        val prefs = Prefs(this)
//        if (prefs.getIsLogin()) {
//            binding.tvStatus.text = "LOGOUT"
//        } else {
//            binding.tvStatus.text = "LOGIN"
//        }
//
//        binding.btnLogin.setOnClickListener {
//            prefs.setIsLogin(true)
//            onBackPressed()
//        }
//
//        binding.btnLogout.setOnClickListener {
//            prefs.setIsLogin(false)
//            onBackPressed()
//        }
    }
}