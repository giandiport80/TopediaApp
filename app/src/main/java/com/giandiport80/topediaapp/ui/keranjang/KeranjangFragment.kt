package com.giandiport80.topediaapp.ui.keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giandiport80.topediaapp.databinding.FragmentHomeBinding
import com.giandiport80.topediaapp.databinding.FragmentKeranjangBinding

class KeranjangFragment : Fragment() {

    private var _binding: FragmentKeranjangBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeranjangBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textHome.text = "Keranjang Fragment"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}