package com.giandiport80.topediaapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.visible
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
            login()
        }
    }

    private fun login() {
        if (binding.editTextEmail.isEmpty()) {
            binding.editTextEmail.error = "Email tidak boleh kosong"
            return
        }

        if (binding.editTextPassword.isEmpty()) {
            binding.editTextPassword.error = "Password tidak boleh kosong"
            return
        }

        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val data = LoginRequest(email, password)
        Log.d("LOGIN_DIKLIK", data.toString())

        viewModel.login(data).observe(this) {
            var message = ""
            when (it.state) {
                State.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    message = "Berhasil login, selamat datang ${it?.data?.name}"
                }

                State.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    message = it.message ?: "Error"
                }

                State.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

            Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}