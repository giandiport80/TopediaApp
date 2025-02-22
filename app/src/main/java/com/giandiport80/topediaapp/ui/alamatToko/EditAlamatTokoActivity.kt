package com.giandiport80.topediaapp.ui.alamatToko

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.giandiport80.topediaapp.core.data.source.model.AlamatToko
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.ActivityTambahAlamatTokoBinding
import com.giandiport80.topediaapp.util.defaultError
import com.giandiport80.topediaapp.util.getTokoId
import com.inyongtisto.myhelper.base.CustomeActivity
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.setOnPositionSelectedListener
import com.inyongtisto.myhelper.extension.showErrorDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditAlamatTokoActivity : CustomeActivity() {
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

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ubah Alamat"

        setupUI()
        mainButton()
    }

    private fun setupUI() {
        val listProvinsi: List<String> =
            listOf("Pilih Provinsi", "Jawa Timur", "Jawa Tengah", "Jawa Barat")
        val listKota: List<String> = listOf("Pilih Kota", "Lamongan", "Semarang", "Bogor")
        val listKecamatan: List<String> =
            listOf("Pilih Kecamatan", "Solokuro", "Ngalian", "Ngaglik")

        binding.spnProvinsi.setOnPositionSelectedListener(this, listProvinsi) {
            if (it == 0) {
                provinsiId = null
            } else {
                provinsiId = 10 // jawa tengah
                provinsi = listProvinsi[it]
            }
        }

        binding.spnKota.setOnPositionSelectedListener(this, listKota) {
            if (it == 0) {
                kotaId = null
            } else {
                kotaId = 399
                kota = listKota[it]
            }
        }

        binding.spnKecamatan.setOnPositionSelectedListener(this, listKecamatan) {
            if (it == 0) {
                kecamatanId = null
            } else {
                kecamatanId = 5505
                kecamatan = listProvinsi[it]
            }
        }
    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.visibility = View.VISIBLE
            lyToolbar.btnSimpan.setOnClickListener {
                if (validate()) {
                    simpanAlamat()
                }
            }

            lyToolbar.btnSimpan.setOnLongClickListener {
                edtLabelLokasi.setText("Rumah")
                edtAlamat.setText("Jalan Kenangan no 12")
                edtKodePos.setText("15520")
                edtPhone.setText("089668958495")
                edtEmail.setText("gian123@gmail.com")
                return@setOnLongClickListener true
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
                    this@EditAlamatTokoActivity,
                    "Harap pilih provinsi",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

            if (kotaId == null) {
                Toast.makeText(
                    this@EditAlamatTokoActivity,
                    "Harap pilih kota",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

            if (kecamatanId == null) {
                Toast.makeText(
                    this@EditAlamatTokoActivity,
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
            tokoId = getTokoId(),
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