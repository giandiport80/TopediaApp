package com.giandiport80.topediaapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.databinding.ItemHomeCategoryBinding
import com.giandiport80.topediaapp.util.toUrlCategory
import com.inyongtisto.myhelper.extension.setImagePicasso

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val data = ArrayList<Category>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<Category>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Category, position: Int) {
            itemBinding.apply {
                textViewName.text = item.name
//                item.image?.let { imageView.setImageResource(it) }
                item.imageReal?.let { imageView.setImagePicasso(item.imageReal.toUrlCategory()) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeCategoryBinding.inflate(
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