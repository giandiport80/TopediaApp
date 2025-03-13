package com.giandiport80.topediaapp.ui.product

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityCreateProductBinding
import com.giandiport80.topediaapp.ui.category.SelectCategoryActivity
import com.giandiport80.topediaapp.ui.product.adapter.AddImageAdapter
import com.giandiport80.topediaapp.util.defaultError
import com.giandiport80.topediaapp.util.getTokoId
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.addRupiahListener
import com.inyongtisto.myhelper.extension.getString
import com.inyongtisto.myhelper.extension.intentActivityResult
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.remove
import com.inyongtisto.myhelper.extension.showErrorDialog
import com.inyongtisto.myhelper.extension.toModel
import com.inyongtisto.myhelper.extension.toMultipartBody
import com.inyongtisto.myhelper.extension.toVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import kotlin.random.Random

class CreateProductActivity : CustomeActivity() {
    private lateinit var binding: ActivityCreateProductBinding
    private val viewModel: ProductViewModel by viewModel()
    private var selectedCategory: Category? = null
    private val adapterImage = AddImageAdapter(
        onAddImage = { pickImage() },
        onDeleteImage = { removeImage(it) }
    )

    private var listImages = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tambah Produk"

        setupUI()
        mainButton()
        setupImageProduct()
    }

    private fun setupUI() {

    }

    private fun setupImageProduct() {
        listImages.add("")
        adapterImage.addItems(listImages)
        binding.rvImage.adapter = adapterImage
    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.visibility = View.VISIBLE
            lyToolbar.btnSimpan.setOnClickListener {
                if (validate()) {
                    create()
                }
            }

            edtHarga.addRupiahListener()

            lyToolbar.btnSimpan.setOnLongClickListener {
                edtName.setText("Mukena Cantik")
                edtHarga.setText(Random.nextInt(10000, 90000).toString())
                edtBerat.setText("1000")
                edtStok.setText("10")
                edtDeskripsi.setText("Deskripsi " + edtName.getString())
                return@setOnLongClickListener true
            }

            edtKategori.setOnClickListener {
                intentActivityResult(SelectCategoryActivity::class.java, launcherCategory)
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtName.isEmpty()) return false
            if (edtHarga.isEmpty()) return false
            if (edtBerat.isEmpty()) return false
            if (edtStok.isEmpty()) return false
            if (edtDeskripsi.isEmpty()) return false
            if (selectedCategory == null) {
                Toast.makeText(this@CreateProductActivity, "Pilih Kategori", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        }

        return true
    }

    private fun create() {
        var images = ""
        listImages.forEach {
            if (it.isNotEmpty()) images += "$it|"
        }
        images = images.dropLast(1)

        val requestData = Product(
            tokoId = getTokoId(),
            name = binding.edtName.text.toString(),
            price = binding.edtHarga.text.toString().remove(",").toInt(),
            description = binding.edtDeskripsi.text.toString(),
            weight = binding.edtBerat.text.toString().toInt(),
            stock = binding.edtStok.text.toString().toInt(),
            imageReal = images,
            categoryId = selectedCategory?.id
        )

        viewModel.createProduct(requestData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    Toast.makeText(this, "Produk berhasil ditambahkan", Toast.LENGTH_SHORT).show()
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

    private val launcherCategory =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val str: String? = it.data?.getStringExtra("extra")
                selectedCategory = str.toModel(Category::class.java)
                binding.edtKategori.setText(selectedCategory?.name)
            }
        }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                val fileImage = File(uri.path!!)
                upload(fileImage)
            }
        }

    private fun removeImage(index: Int) {
        listImages.removeAt(index)
        adapterImage.removeAt(index)

        if (!listImages.any { it.isEmpty() }) {
            listImages.add("")
            adapterImage.addItems(listImages)
            binding.btnTambahFoto.toVisible()
        }
    }

    private fun upload(fileImage: File) {
        val file = fileImage.toMultipartBody()

        if (file != null) {
            viewModel.uploadProduct(file).observe(this) { it ->
                when (it.state) {
                    State.SUCCESS -> {
                        progress.dismiss()

                        val tempImage =
                            listImages.filter { image -> image.isNotEmpty() } as ArrayList
                        tempImage.add(it.data!!)

                        if (tempImage.size < 5) {
                            tempImage.add("")
                        } else {
                            binding.btnTambahFoto.visibility = View.GONE
                        }

                        listImages = tempImage
                        adapterImage.addItems(tempImage)
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