package com.giandiport80.topediaapp.ui.product.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.R
import com.giandiport80.topediaapp.databinding.ItemGambarBinding
import com.giandiport80.topediaapp.util.toUrlProduct
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.inyongtisto.myhelper.extension.visible

class AddImageAdapter(
    var onAddImage: () -> Unit,
    var onDeleteImage: (Int) -> Unit
) :
    RecyclerView.Adapter<AddImageAdapter.ViewHolder>() {
    private val data = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<String>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun removeAt(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    inner class ViewHolder(private val itemBinding: ItemGambarBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: String, position: Int) {
            itemBinding.apply {
                if (item.isNotEmpty()) {
                    btnAddFoto.setImagePicasso(item.toUrlProduct())
                } else {
                    btnAddFoto.setImageResource(R.drawable.asset_addphotos)
                }

//                btnClose.visible(item.isNotEmpty())
                btnClose.visible(item.isNotEmpty())

                btnClose.setOnClickListener {
                    onDeleteImage(adapterPosition)
                }

                btnAddFoto.setOnClickListener {
                    onAddImage()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGambarBinding.inflate(
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