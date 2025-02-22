package com.giandiport80.topediaapp.ui.alamatToko

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.core.data.source.model.AlamatToko
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityTambahAlamatTokoBinding
import com.giandiport80.topediaapp.util.defaultError
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.showErrorDialog
import com.inyongtisto.myhelper.extension.showLoading
import org.koin.androidx.viewmodel.ext.android.viewModel

class TambahAlamatTokoActivity : CustomeActivity() {
    private lateinit var binding: ActivityTambahAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private var provinsiId: Int? = null
    private var kotaId: Int? = null
    private var kecamatanId: Int? = null
    private var provinsi: String? = null
    private var kota: String? = null
    private var kecamatan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambahAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.visibility = View.VISIBLE
            lyToolbar.btnSimpan.setOnClickListener {
                if (validate()) {
                    simpanAlamat()
                }
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtLabelLokasi.isEmpty()) return false
            if (edtAlamat.isEmpty()) return false
            if (edtEmail.isEmpty()) return false
            if (edtPhone.isEmpty()) return false

            if (provinsiId == null) {
                Toast.makeText(
                    this@TambahAlamatTokoActivity,
                    "Harap pilih provinsi",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

            if (kotaId == null) {
                Toast.makeText(
                    this@TambahAlamatTokoActivity,
                    "Harap pilih kota",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

            if (kecamatanId == null) {
                Toast.makeText(
                    this@TambahAlamatTokoActivity,
                    "Harap pilih kecamatan",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }

        return true
    }

    private fun simpanAlamat() {
        val requestData = AlamatToko(
            label = binding.edtLabelLokasi.text.toString(),
            alamat = binding.edtAlamat.text.toString(),
            provinsi = provinsi,
            provinsiId = provinsiId,
            kota = kota,
            kotaId = kotaId,
            kecamatan = kecamatan,
            kecamatanId = kecamatanId,
            kodepos = binding.edtKodePos.text.toString(),
            email = binding.edtEmail.text.toString(),
            phone = binding.edtPhone.text.toString(),
        )
        viewModel.createAlamatToko(requestData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    Toast.makeText(this, "Alamat berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    onBackPressedDispatcher.onBackPressed()
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