package com.giandiport80.topediaapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giandiport80.topediaapp.MyFragment
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.FragmentHomeBinding
import com.giandiport80.topediaapp.ui.home.adapter.CategoryAdapter
import com.giandiport80.topediaapp.ui.home.adapter.ProductTerbaruAdapter
import com.giandiport80.topediaapp.ui.home.adapter.ProductTerlarisAdapter
import com.giandiport80.topediaapp.ui.home.adapter.SliderBaruAdapter
import com.giandiport80.topediaapp.ui.product.DetailProductActivity
import com.giandiport80.topediaapp.ui.product.ProductViewModel
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setDefaultColor
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : MyFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val viewModelProduct: ProductViewModel by viewModel()
    private val binding get() = _binding!!
    private val adapterCategory = CategoryAdapter()
    private val adapterBaruSlider = SliderBaruAdapter()
    private val adapterProductTerlaris = ProductTerlarisAdapter {
        detailProduct(it)
    }
    private val adapterProductTerbaru = ProductTerbaruAdapter {
        detailProduct(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupSlider()
        setupAdapter()
//        setData()
        mainButton()
        getHome()

        return root
    }

    private fun getHome() {
        homeViewModel.getHome().observe(requireActivity()) {
            when (it.state) {
                State.SUCCESS -> {
                    Log.d("GETHOME", "getHome: " + it.toJson())
                    val categories = it.data?.categories ?: emptyList()
                    val products = it.data?.products ?: emptyList()
                    val sliders = it.data?.sliders ?: emptyList()
//
                    adapterCategory.addItems(categories)
                    adapterBaruSlider.addItems(sliders)
                    adapterProductTerlaris.addItems(products)
                    adapterProductTerbaru.addItems(products)

                    binding.apply {
                        pdCategory.visibility = View.GONE
                        pdSlider.visibility = View.GONE
                        pdProductTerbaru.visibility = View.GONE
                        pdProductTerlaris.visibility = View.GONE
                        swipeRefresh.isRefreshing = false
                    }
                }

                State.ERROR -> {
                    binding.apply {
                        pdCategory.visibility = View.GONE
                        pdSlider.visibility = View.GONE
                        pdProductTerbaru.visibility = View.GONE
                        pdProductTerlaris.visibility = View.GONE
                        swipeRefresh.isRefreshing = false
                    }
                }

                State.LOADING -> {
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvCategory.adapter = adapterCategory
        binding.rvProductTerlaris.adapter = adapterProductTerlaris
        binding.rvProductTerbaru.adapter = adapterProductTerbaru
    }

    private fun setupSlider() {
        binding.apply {
            slider.adapter = adapterBaruSlider
            slider.setPadding(40, 0, 40, 0)
        }
    }

    private fun setData() {
        homeViewModel.listCategory.observe(viewLifecycleOwner) {
            adapterCategory.addItems(it)
        }

        homeViewModel.listSlider.observe(viewLifecycleOwner) {
            adapterBaruSlider.addItems(it)
        }

        homeViewModel.listProduct.observe(viewLifecycleOwner) {
            adapterProductTerlaris.addItems(it)
            adapterProductTerbaru.addItems(it)
        }

    }

    private fun mainButton() {
        binding.swipeRefresh.setDefaultColor()
        binding.swipeRefresh.setOnRefreshListener {
            getHome()
        }
    }

    private fun detailProduct(product: Product) {
        viewModelProduct.getOneProduct(product.id).observe(requireActivity()) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    intentActivity(DetailProductActivity::class.java, it.data)
                }

                State.ERROR -> {
                    toastError(it.message)
                    progress.dismiss()
                }

                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}