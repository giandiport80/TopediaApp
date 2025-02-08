package com.giandiport80.topediaapp.ui.profile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest
import com.giandiport80.topediaapp.databinding.ActivityUpdateProfileBinding
import com.giandiport80.topediaapp.ui.auth.AuthViewModel
import com.giandiport80.topediaapp.util.Constant
import com.giandiport80.topediaapp.util.Helper
import com.giandiport80.topediaapp.util.Prefs
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.toMultipartBody
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpdateProfileActivity : CustomeActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private val viewModel: AuthViewModel by viewModel()
    private var fileImage: File? = null

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
            if (fileImage != null) {
                upload()
            }
        }

        binding.imageProfile.setOnClickListener {
            pickImage()
        }
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                editTextNama.setText(user.name)
                editTextEmail.setText(user.email)
                editTextPhone.setText(user.phone)
                textViewInisial.text = Helper.getInitialName(user.name)
                Picasso.get().load(Constant.USER_URL + user.image)
                    .into(binding.imageProfile)

                Log.d("IMAGE_USER", Constant.USER_URL + user.image)
            }
        }
    }

    private fun update() {
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

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                fileImage = File(uri.path ?: "")
                Picasso.get().load(uri)
                    .into(binding.imageProfile)
            }
        }

    private fun pickImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(620, 620)
            .createIntentFromDialog {
                launcher.launch(it)
            }
    }

    private fun upload() {
        val idUser = Prefs.getUser()?.id
        val file = fileImage.toMultipartBody()

        if (idUser != null) {
            if (file != null) {
                viewModel.uploadImageUser(idUser, file).observe(this) {
                    when (it.state) {
                        State.SUCCESS -> {
                            update()
                            Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        State.ERROR -> {
                            Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT)
                                .show()
                            progress.dismiss()
                        }

                        State.LOADING -> {
                            progress.show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Image tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "ID user tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}