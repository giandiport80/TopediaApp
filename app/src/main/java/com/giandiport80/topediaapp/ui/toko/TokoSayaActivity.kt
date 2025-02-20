package com.giandiport80.topediaapp.ui.toko

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giandiport80.topediaapp.databinding.ActivityTokoSayaBinding
import com.giandiport80.topediaapp.ui.alamatToko.ListAlamatTokoActivity
import com.giandiport80.topediaapp.util.Constant
import com.giandiport80.topediaapp.util.Helper
import com.giandiport80.topediaapp.util.Prefs
import com.squareup.picasso.Picasso

class TokoSayaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTokoSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.lyToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Toko Saya"

        setData()
        setupListener()
    }

    private fun setupListener() {
        binding.apply {
            btnAlamat.setOnClickListener {
                val intent = Intent(this@TokoSayaActivity, ListAlamatTokoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                if (user.toko != null) {
                    textViewName.text = user.toko!!.nama
                    textViewInisial.text = Helper.getInitialName(user.toko!!.nama)
                    Picasso.get().load(Constant.USER_URL + user.toko!!.image)
                        .into(binding.imageProfile)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}