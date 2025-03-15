package com.giandiport80.topediaapp.ui.slider

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.giandiport80.topediaapp.core.data.repository.BaseViewModel
import com.giandiport80.topediaapp.core.data.source.model.Slider
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.core.data.source.remote.request.SliderRequest
import com.giandiport80.topediaapp.databinding.ActivityCreateSliderBinding
import com.giandiport80.topediaapp.ui.product.adapter.AddImageAdapter
import com.giandiport80.topediaapp.util.defaultError
import com.giandiport80.topediaapp.util.toUrlSlider
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.extra
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.inyongtisto.myhelper.extension.showErrorDialog
import com.inyongtisto.myhelper.extension.toMultipartBody
import com.inyongtisto.myhelper.extension.toastWarning
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class CreateSliderActivity : CustomeActivity() {
    private lateinit var binding: ActivityCreateSliderBinding
    private val viewModel: SliderViewModel by viewModel()
    private val viewModelBase: BaseViewModel by viewModel()
    private val slider by extra<Slider>()

    private val adapterImage = AddImageAdapter(
        onAddImage = { pickImage() },
        onDeleteImage = { removeImage(it) }
    )
    private var fileImage: File? = null

    private var listImages = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = if (slider == null) "Tambah Slider" else "Edit Slider"

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

        setupUI()
        mainButton()
    }

    private fun setupUI() {
        slider?.let {
            binding.apply {
                edtName.setText(it.name)
                imageSlider.setImagePicasso(it.imageReal.toUrlSlider())
            }
        }
    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.visibility = View.VISIBLE
            lyToolbar.btnSimpan.setOnClickListener {
                if (slider == null) {
                    if (validate()) {
                        upload()
                    }
                } else {
                    if (fileImage != null) {
                        upload()
                    } else {
                        update()
                    }
                }
            }

            btnAdd.setOnClickListener {
                pickImage()
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtName.isEmpty()) return false
            if (fileImage == null) {
                toastWarning("Pilih gambar kategori")
                return false
            }
        }

        return true
    }

    private fun create(imageName: String) {
        val requestData = SliderRequest(
            name = binding.edtName.text.toString(),
            image = imageName
        )

        viewModel.create(requestData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    Toast.makeText(this, "Slider berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    finish()
                }

                State.ERROR -> {
                    progress.dismiss()
                    showErrorDialog(it.message.defaultError())
                }

                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    private fun update(imageName: String? = null) {
        val requestData = SliderRequest(
            id = slider?.id,
            name = binding.edtName.text.toString(),
            image = imageName
        )

        viewModel.update(requestData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    Toast.makeText(this, "Slider berhasil diubah", Toast.LENGTH_SHORT).show()
                    finish()
                }

                State.ERROR -> {
                    progress.dismiss()
                    showErrorDialog(it.message.defaultError())
                }

                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                fileImage = File(uri.path!!)

                Picasso.get().load(fileImage!!).into(binding.imageSlider)
            }
        }

    private fun removeImage(index: Int) {
        listImages.removeAt(index)
        adapterImage.removeAt(index)

        if (!listImages.any { it.isEmpty() }) {
            listImages.add("")
            adapterImage.addItems(listImages)
        }
    }

    private fun upload() {
        val file = fileImage.toMultipartBody()

        if (file != null) {
            viewModelBase.upload("slider", file).observe(this) {
                when (it.state) {
                    State.SUCCESS -> {
                        progress.dismiss()
                        val imageName = it.data
                        if (imageName != null) {
                            if (slider == null) {
                                create(imageName)
                            } else {
                                update(imageName)
                            }
                        }
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
    }

    private fun pickImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(620, 620)
            .createIntentFromDialog {
                launcher.launch(it)
            }
    }
}