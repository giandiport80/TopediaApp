package com.giandiport80.topediaapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.databinding.ItemHomeProdukTerbaruBinding
import com.giandiport80.topediaapp.ui.product.DetailProductActivity
import com.giandiport80.topediaapp.util.GenericDiffCallback
import com.giandiport80.topediaapp.util.Helper
import com.giandiport80.topediaapp.util.toUrlProduct
import com.inyongtisto.myhelper.extension.coret
import com.inyongtisto.myhelper.extension.def
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toRupiah
import com.inyongtisto.myhelper.extension.toVisible
import com.inyongtisto.myhelper.extension.visible

class ProductTerbaruAdapter(
    var onClick: (product: Product) -> Unit
) :
    RecyclerView.Adapter<ProductTerbaruAdapter.ViewHolder>() {
    private val data = ArrayList<Product>()

    @SuppressLint("NotifyDataSetChanged")
    public fun addItems(items: List<Product>) {
//        data.addAll(items)
//        notifyDataSetChanged()

        val diffResult = DiffUtil.calculateDiff(
            GenericDiffCallback(
                data,
                items,
                areItemsTheSame = { old, new -> old.id == new.id },
                areContentsTheSame = { old, new -> old == new })
        )

        data.clear()
        data.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val itemBinding: ItemHomeProdukTerbaruBinding) :
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

                lyMain.setOnClickListener {
                    onClick(item)
//                    root.context.intentActivity(DetailProductActivity::class.java, item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeProdukTerbaruBinding.inflate(
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