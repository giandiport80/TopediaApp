package com.giandiport80.topediaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giandiport80.topediaapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setData()
        mainButton()

        return root
    }

    private fun setData() {
        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }
    }

    private fun mainButton() {
        binding.btnKlik.setOnClickListener {
            homeViewModel.ubahData("ini dari view model")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}