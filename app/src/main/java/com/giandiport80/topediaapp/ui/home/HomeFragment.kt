package com.giandiport80.topediaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giandiport80.topediaapp.databinding.FragmentHomeBinding
import com.giandiport80.topediaapp.ui.home.adapter.CategoryAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private val binding get() = _binding!!
    private val adapterCategory = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()
        setData()
        mainButton()

        return root
    }

    private fun setupAdapter() {
        binding.rvCategory.adapter = adapterCategory
    }

    private fun setData() {
        homeViewModel.listCategory.observe(viewLifecycleOwner) {
            adapterCategory.addItems(it)
        }
    }

    private fun mainButton() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}