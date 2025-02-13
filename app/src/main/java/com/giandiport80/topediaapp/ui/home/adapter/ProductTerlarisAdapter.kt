package com.giandiport80.topediaapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.databinding.ItemHomeProdukTerlarisBinding
import com.giandiport80.topediaapp.util.Helper
import com.inyongtisto.myhelper.extension.coret
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toRupiah
import com.inyongtisto.myhelper.extension.toVisible

class ProductTerlarisAdapter : RecyclerView.Adapter<ProductTerlarisAdapter.ViewHolder>() {
    private val data = ArrayList<Product>()

    @SuppressLint("NotifyDataSetChanged")
    public fun addItems(items: List<Product>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemHomeProdukTerlarisBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Product, position: Int) {
            itemBinding.apply {
                val harga = item.harga ?: 0
                imageView.setImageResource(item.image)
                tvName.text = item.name
                tvHarga.text = Helper.toRupiah(item.harga)
                tvPengiriman.text = item.pengiriman
                tvRating.text = "" + item.rating + " | Terjual " + item.sold

                if (item.discount != 0) {
                    lyGrosir.toGone()
                    lyDiskon.toVisible()
                    tvDiskon.text = "${item.discount}%"

                    tvHarga.text = "120.000"
                    tvHargaAsli.text = Helper.toRupiah(item.harga)
                    tvHargaAsli.coret()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeProdukTerlarisBinding.inflate(
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