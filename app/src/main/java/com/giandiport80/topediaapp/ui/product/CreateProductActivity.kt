package com.giandiport80.topediaapp.ui.product

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityCreateProductBinding
import com.giandiport80.topediaapp.util.defaultError
import com.giandiport80.topediaapp.util.getTokoId
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.addRupiahListener
import com.inyongtisto.myhelper.extension.getString
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.remove
import com.inyongtisto.myhelper.extension.showErrorDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class CreateProductActivity : CustomeActivity() {
    private lateinit var binding: ActivityCreateProductBinding
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tambah Alamat"

        setupUI()
        mainButton()
    }

    private fun setupUI() {

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
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtName.isEmpty()) return false
            if (edtHarga.isEmpty()) return false
            if (edtBerat.isEmpty()) return false
            if (edtStok.isEmpty()) return false
            if (edtDeskripsi.isEmpty()) return false
        }

        return true
    }

    private fun create() {
        val requestData = Product(
            tokoId = getTokoId(),
            name = binding.edtName.text.toString(),
            price = binding.edtHarga.text.toString().remove(",").toInt(),
            description = binding.edtDeskripsi.text.toString(),
            weight = binding.edtBerat.text.toString().toInt(),
            stock = binding.edtStok.text.toString().toInt(),
            imageReal = "testing.jpg"
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
}