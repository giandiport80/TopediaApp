package com.giandiport80.topediaapp.ui.product.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.core.data.source.model.AlamatToko
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.databinding.ItemAlamatTokoBinding
import com.giandiport80.topediaapp.databinding.ItemProductTokoBinding
import com.giandiport80.topediaapp.ui.alamatToko.EditAlamatTokoActivity
import com.giandiport80.topediaapp.util.toUrlProduct
import com.inyongtisto.myhelper.extension.def
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toRupiah
import com.squareup.picasso.Picasso

class ProductTokoAdapter(
    val onDelete: (item: Product, position: Int) -> Unit,
    val onClick: (item: Product) -> Unit,
) :
    RecyclerView.Adapter<ProductTokoAdapter.ViewHolder>() {
    private val data = ArrayList<Product>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<Product>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemProductTokoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Product) {
            itemBinding.apply {
                tvName.text = item.name
                tvHarga.text = item.price.toRupiah()
                tvStok.text = item.stock.toString()

                val splitImages = item.imageReal?.split("|")
                val imageProduct = if (splitImages.isNullOrEmpty()) {
                    item.imageReal.def("")
                } else {
                    splitImages[0]
                }

                imgProduct.setImagePicasso(imageProduct.toUrlProduct())

                btnEdit.setOnClickListener {
                    onClick(item)
                }

                lyMain.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    fun removeAt(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductTokoBinding.inflate(
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
        holder.bind(data[position])
    }
}