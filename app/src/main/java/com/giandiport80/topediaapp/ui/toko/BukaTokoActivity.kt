package com.giandiport80.topediaapp.ui.toko

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.core.data.source.remote.request.CreateTokoRequest
import com.giandiport80.topediaapp.databinding.ActivityBukaTokoBinding
import com.giandiport80.topediaapp.util.Prefs
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.showLoading
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class BukaTokoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBukaTokoBinding
    private val viewModel: TokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        mainButton()
    }

    private fun mainButton() {
        binding.btnBukaToko.setOnClickListener {
            bukaToko()
        }
    }

    private fun bukaToko() {
        val data = CreateTokoRequest(
            userId = Prefs.getUser()?.id ?: 0,
            nama = binding.edtName.text.toString(),
            kota = binding.edtLokasi.text.toString()
        )

        viewModel.createToko(data).observe(this) {
            var message = ""
            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()

                    val body = it.data
                    Toast.makeText(this, "Nama Toko: " + body?.nama, Toast.LENGTH_SHORT).show()

                    val i = Intent(this@BukaTokoActivity, TokoSayaActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                }

                State.ERROR -> {
                    dismisLoading()
                    message = it.message ?: "Error"
                    toastError(message)
                }

                State.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}