package com.giandiport80.topediaapp.ui.product

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.databinding.ActivityDetailProductBinding
import com.giandiport80.topediaapp.ui.product.adapter.ImageProductSliderAdapter
import com.inyongtisto.myhelper.extension.coret
import com.inyongtisto.myhelper.extension.extra
import com.inyongtisto.myhelper.extension.formatCurrency
import com.inyongtisto.myhelper.extension.toRupiah
import com.inyongtisto.myhelper.extension.visible

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private val product by extra<Product>()
    private val adapter = ImageProductSliderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product?.let {
            binding.tvNameProduct.text = it.name
        }

        mainButton()
        setAdapter()
        setupData()
    }

    private fun setAdapter() {
        binding.apply {
            val image = product?.imageReal ?: ""
            val splitImages = image.split("|")
            adapter.addItems(splitImages)
            sliderImage.adapter = adapter
        }
    }

    private fun setupData() {
        binding.apply {
            product?.let {
                val harga = it.price ?: 0
                tvPrice.text = harga.toRupiah()
                tvStok.text = it.stock.formatCurrency()
                tvSold.text = it.sold.formatCurrency()
                tvCity.text = it.toko?.kota
                tvNameStore.text = it.toko?.nama
                tvDescription.text = it.description
                if (it.discount != 0) {
                    val discount = it.discount?.toDouble()
                    if (discount != null) {
                        lyDiskon.visible(discount > 0)
                        tvDiscount.text = "${it.discount}%"
                        tvPrice.text = (harga - ((discount / 100) * harga)).toRupiah()
                    }

                    tvHargaAsli.text = it.price.toRupiah()
                    tvHargaAsli.coret()
                }
            }
        }
    }

    private fun mainButton() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}