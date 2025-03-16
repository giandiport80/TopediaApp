package com.giandiport80.topediaapp.ui.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.giandiport80.topediaapp.core.data.source.model.Slider
import com.giandiport80.topediaapp.databinding.ItemImageProductSliderBinding
import com.giandiport80.topediaapp.util.toUrlProduct
import com.giandiport80.topediaapp.util.toUrlSlider
import com.inyongtisto.myhelper.extension.setImagePicasso

class ImageProductSliderAdapter() : PagerAdapter() {
    private val data: ArrayList<String> = ArrayList()

    fun addItems(items: List<String>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding =
            ItemImageProductSliderBinding.inflate(
                LayoutInflater.from(container.context),
                container,
                false
            )

        val item = data[position]
        binding.apply {
//            imageView.setImageResource(item.image)
            imageProduct.setImagePicasso(item.toUrlProduct())
        }

        container.addView(binding.root)

        return binding.root
//        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
//        super.destroyItem(container, position, `object`)
    }
}