package com.giandiport80.topediaapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giandiport80.topediaapp.core.data.source.model.Slider
import com.giandiport80.topediaapp.databinding.ItemHomeSliderBinding
import com.giandiport80.topediaapp.util.toUrlSlider
import com.inyongtisto.myhelper.extension.setImagePicasso

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {
    private val data = ArrayList<Slider>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<Slider>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: ItemHomeSliderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Slider, position: Int) {
            itemBinding.apply {
                imageView.setImagePicasso(item.imageReal.toUrlSlider())
//                imageView.setImageResource(item.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeSliderBinding.inflate(
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