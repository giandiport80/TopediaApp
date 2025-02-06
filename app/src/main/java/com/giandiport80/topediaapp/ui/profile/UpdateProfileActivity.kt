package com.giandiport80.topediaapp.ui.profile

import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.R
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest
import com.giandiport80.topediaapp.databinding.ActivityUpdateProfileBinding
import com.giandiport80.topediaapp.ui.auth.AuthViewModel
import com.giandiport80.topediaapp.util.Prefs
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.setToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Profile"

        setData()
        mainButton()
    }

    private fun mainButton() {
        binding.btnSimpan.setOnClickListener {
            register()
        }
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                editTextNama.setText(user.name)
                editTextEmail.setText(user.email)
                editTextPhone.setText(user.phone)
            }
        }
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

        if (binding.editTextPhone.isEmpty()) {
            binding.editTextPhone.error = "No. telepon tidak boleh kosong"
            return
        }

        val data = UpdateProfileRequest(
            id = 1,
            name = binding.editTextNama.text.toString(),
            email = binding.editTextEmail.text.toString(),
            phone = binding.editTextPhone.text.toString()
        )

//        viewModel.register(data).observe(this) {
//            var message = ""
//            when (it.state) {
//                State.SUCCESS -> {
//                    dismisLoading()
//                    message = "Berhasil daftar, silahkan login"
//
//                    val i = Intent(this@UpdateProfileActivity, LoginActivity::class.java)
//                    startActivity(i)
//                }
//
//                State.ERROR -> {
//                    dismisLoading()
//                    message = it.message ?: "Error"
//                }
//
//                State.LOADING -> {
//                    showLoading()
//                }
//            }
//
//            Toast.makeText(
//                this,
//                message,
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}