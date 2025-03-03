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
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toRupiah

class ProductTokoAdapter(var onDelete: (item: Product, position: Int) -> Unit) :
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
        fun bind(item: Product, position: Int) {
            itemBinding.apply {
                val context = root.context
                tvName.text = item.name
                tvHarga.text = item.price.toRupiah()
                tvStok.text = item.stock.toString()

                val splitImages = item.imageReal?.split("|")
                splitImages?.forEach {

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
        holder.bind(data[position], position)
    }
}