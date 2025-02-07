package com.giandiport80.topediaapp.ui.profile

import android.os.Bundle
import android.widget.Toast
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest
import com.giandiport80.topediaapp.databinding.ActivityUpdateProfileBinding
import com.giandiport80.topediaapp.ui.auth.AuthViewModel
import com.giandiport80.topediaapp.util.Prefs
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.showLoading
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileActivity : CustomeActivity() {
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

        val idUser = Prefs.getUser()?.id
        val data = UpdateProfileRequest(
            id = idUser ?: 0,
            name = binding.editTextNama.text.toString(),
            email = binding.editTextEmail.text.toString(),
            phone = binding.editTextPhone.text.toString()
        )

        viewModel.updateUser(data).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
                    progress.dismiss()
                    onBackPressedDispatcher.onBackPressed()
                }

                State.ERROR -> {
                    Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
                    progress.dismiss()
                }

                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}