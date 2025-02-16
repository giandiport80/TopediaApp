package com.giandiport80.topediaapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.databinding.ActivityRegisterBinding
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.showLoading
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        mainButton()
    }

    private fun mainButton() {
        binding.btnDaftar.setOnClickListener {
            register()
        }
    }

    private fun setData() {

    }

    private fun register() {
        if (binding.editTextNama.isEmpty()) {
            binding.editTextNama.error = "Nama tidak boleh kosong"
            return
        }

        if (binding.editTextEmail.isEmpty()) {
            binding.editTextEmail.error = "Email tidak boleh kosong"
            return
        }

        if (binding.editTextPassword.isEmpty()) {
            binding.editTextPassword.error = "Password tidak boleh kosong"
            return
        }

        if (binding.editTextPhone.isEmpty()) {
            binding.editTextPhone.error = "No. telepon tidak boleh kosong"
            return
        }

        val data = RegisterRequest(
            name = binding.editTextNama.text.toString(),
            email = binding.editTextEmail.text.toString(),
            phone = binding.editTextPhone.text.toString(),
            password = binding.editTextPassword.text.toString()
        )

        viewModel.register(data).observe(this) {
            var message = ""
            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    message = "Berhasil daftar, silahkan login"

                    val i = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(i)
                }

                State.ERROR -> {
                    dismisLoading()
                    message = it.message ?: "Error"
                }

                State.LOADING -> {
                    showLoading()
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