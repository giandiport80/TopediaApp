package com.giandiport80.topediaapp.ui.alamatToko.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.core.data.source.model.AlamatToko
import com.giandiport80.topediaapp.databinding.ItemAlamatTokoBinding
import com.giandiport80.topediaapp.ui.alamatToko.EditAlamatTokoActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.toJson

class AlamatTokoAdapter : RecyclerView.Adapter<AlamatTokoAdapter.ViewHolder>() {
    private val data = ArrayList<AlamatToko>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<AlamatToko>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemAlamatTokoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: AlamatToko, position: Int) {
            itemBinding.apply {
                tvKota.text = item.kota

                val kecamatan = item.kecamatan?.let { "Kec. $it" } ?: ""

                tvAlamat.text =
                    "${item.alamat}, $kecamatan Kab/Kota ${item.kota}, Provinsi ${item.provinsi} kodepos ${item.kodepos}"
                tvEmail.text = item.email ?: "-"
                tvPhone.text = item.phone ?: "-"

                val context = root.context
                btnMenu.setOnClickListener {
                    val listMenu = listOf("Detail", "Hapus")
                    context.popUpMenu(btnMenu, listMenu) {
                        when (it) {
                            "Detail" -> {
                                context.intentActivity(
                                    EditAlamatTokoActivity::class.java,
                                    item.toJson()
                                )
                            }

                            "Hapus" -> logs("Hapus")
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAlamatTokoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }
}