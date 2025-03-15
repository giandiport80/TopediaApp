package com.giandiport80.topediaapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.databinding.ItemHomeProdukTerlarisBinding
import com.giandiport80.topediaapp.util.Helper
import com.giandiport80.topediaapp.util.toUrlProduct
import com.inyongtisto.myhelper.extension.coret
import com.inyongtisto.myhelper.extension.def
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toRupiah
import com.inyongtisto.myhelper.extension.toVisible
import com.inyongtisto.myhelper.extension.visible

class ProductTerlarisAdapter : RecyclerView.Adapter<ProductTerlarisAdapter.ViewHolder>() {
    private val data = ArrayList<Product>()

    @SuppressLint("NotifyDataSetChanged")
    public fun addItems(items: List<Product>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemHomeProdukTerlarisBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Product, position: Int) {
            itemBinding.apply {
                val harga = item.harga ?: 0
//                item.image?.let { imageView.setImageResource(it) }
                imageView.setImagePicasso(item.firstImage().toUrlProduct())
                tvName.text = item.name
                tvHarga.text = item.harga?.let { Helper.toRupiah(it) }
                tvPengiriman.text = item.pengiriman ?: "Jakarta Pusat"
                tvRating.text = "" + (item.rating.def(5.0).toInt()) + " | Terjual " + item.sold

                if (item.discount != null) {
                    val hargaSetelahDiskon =
                        (harga - ((item.discount.toDouble() / 100) * harga)).toRupiah()
                    lyGrosir.toGone()
                    lyDiskon.visible(item.discount > 0)
                    tvDiskon.text = "${item.discount}%"

                    tvHarga.text = hargaSetelahDiskon
                    tvHargaAsli.text = item.harga?.let { Helper.toRupiah(it) }
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