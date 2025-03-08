package com.giandiport80.topediaapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.ui.navigation.NavigationActivity
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.showLoading
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : CustomeActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: AuthViewModel by viewModel() // di

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setData()
        mainButton()
    }

    private fun setData() {

    }

    private fun mainButton() {
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnDaftar.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
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
                    progress.dismiss()
                    message = "Berhasil login, selamat datang ${it?.data?.name}"

                    val i = Intent(this@LoginActivity, NavigationActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                }

                State.ERROR -> {
                    progress.dismiss()
                    message = it.message ?: "Error"
                }

                State.LOADING -> {
                    progress.show()
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