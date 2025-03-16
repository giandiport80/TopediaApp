package com.giandiport80.topediaapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giandiport80.topediaapp.core.data.source.remote.network.State
import com.giandiport80.topediaapp.databinding.FragmentHomeBinding
import com.giandiport80.topediaapp.ui.home.adapter.CategoryAdapter
import com.giandiport80.topediaapp.ui.home.adapter.ProductTerbaruAdapter
import com.giandiport80.topediaapp.ui.home.adapter.ProductTerlarisAdapter
import com.giandiport80.topediaapp.ui.home.adapter.SliderBaruAdapter
import com.inyongtisto.myhelper.extension.setDefaultColor
import com.inyongtisto.myhelper.extension.toJson
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val binding get() = _binding!!
    private val adapterCategory = CategoryAdapter()
    private val adapterBaruSlider = SliderBaruAdapter()
    private val adapterProductTerlaris = ProductTerlarisAdapter()
    private val adapterProductTerbaru = ProductTerbaruAdapter()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}