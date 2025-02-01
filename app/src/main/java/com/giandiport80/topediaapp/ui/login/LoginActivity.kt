package com.giandiport80.topediaapp.ui.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.databinding.ActivityLoginBinding
import com.giandiport80.topediaapp.util.Prefs

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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