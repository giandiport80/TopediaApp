package com.giandiport80.topediaapp.ui.category.adapter

import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.databinding.ItemCategoryAdminBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.util.toUrlCategory
import com.inyongtisto.myhelper.extension.*

@SuppressLint("NotifyDataSetChanged")
class CategoryAdminAdapter(
    val onClick: (item: Category) -> Unit,
    val onDelete: (item: Category, pos: Int) -> Unit
) : RecyclerView.Adapter<CategoryAdminAdapter.ViewHolder>() {

    private var data = ArrayList<Category>()

    inner class ViewHolder(private val itemBinding: ItemCategoryAdminBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Category, position: Int) {
            itemBinding.apply {
                tvName.text = item.name

                val imageProduct = item.imageReal

                imageView.setImagePicasso(imageProduct.toUrlCategory())

                btnEdit.setOnClickListener {
                    onClick.invoke(item)
                }

                lyMain.setOnClickListener {
                    onClick.invoke(item)
                }

                btnDelete.setOnClickListener {
                    onDelete.invoke(item, adapterPosition)
                }
            }
        }
    }

    fun removeAt(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addItems(items: List<Category>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryAdminBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}