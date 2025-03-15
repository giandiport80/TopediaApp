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
import com.giandiport80.topediaapp.ui.home.adapter.SliderAdapter
import com.inyongtisto.myhelper.extension.toJson
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val binding get() = _binding!!
    private val adapterCategory = CategoryAdapter()
    private val adapterSlider = SliderAdapter()
    private val adapterProductTerlaris = ProductTerlarisAdapter()
    private val adapterProductTerbaru = ProductTerbaruAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()
        setData()
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
//
                    adapterCategory.addItems(categories)
                    adapterProductTerlaris.addItems(products)
                    adapterProductTerbaru.addItems(products)
                }

                State.ERROR -> {

                }

                State.LOADING -> {

                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvCategory.adapter = adapterCategory
        binding.rvSlider.adapter = adapterSlider
        binding.rvProductTerlaris.adapter = adapterProductTerlaris
        binding.rvProductTerbaru.adapter = adapterProductTerbaru
    }

    private fun setData() {
        homeViewModel.listCategory.observe(viewLifecycleOwner) {
            adapterCategory.addItems(it)
        }

        homeViewModel.listSlider.observe(viewLifecycleOwner) {
            adapterSlider.addItems(it)
        }

        homeViewModel.listProduct.observe(viewLifecycleOwner) {
            adapterProductTerlaris.addItems(it)
            adapterProductTerbaru.addItems(it)
        }
    }

    private fun mainButton() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}