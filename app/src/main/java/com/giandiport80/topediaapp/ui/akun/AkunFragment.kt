package com.giandiport80.topediaapp.ui.akun

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giandiport80.topediaapp.ui.navigation.NavigationActivity
import com.giandiport80.topediaapp.databinding.FragmentAkunBinding
import com.giandiport80.topediaapp.ui.adminpanel.AdminPanelActivity
import com.giandiport80.topediaapp.ui.profile.UpdateProfileActivity
import com.giandiport80.topediaapp.ui.toko.BukaTokoActivity
import com.giandiport80.topediaapp.ui.toko.TokoSayaActivity
import com.giandiport80.topediaapp.util.Constant
import com.giandiport80.topediaapp.util.Helper
import com.giandiport80.topediaapp.util.Prefs
import com.inyongtisto.myhelper.extension.visible
import com.squareup.picasso.Picasso

class AkunFragment : Fragment() {

    private var _binding: FragmentAkunBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUser()
        mainButton()

        return root
    }

    private fun mainButton() {
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            val i = Intent(context, NavigationActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(context, UpdateProfileActivity::class.java)
            startActivity(intent)
        }


    }

    private fun setUser() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                textViewName.text = user.name
                textViewEmail.text = user.email
                textViewPhone.text = user.phone
                textViewInisial.text = Helper.getInitialName(user.name)
                Picasso.get().load(Constant.USER_URL + user.image)
                    .into(binding.imageProfile)

                if (user.toko != null) {
                    tvStatusToko.visibility = View.GONE
                    tvNameToko.text = user.toko!!.nama

                    binding.btnToko.setOnClickListener {
                        val intent = Intent(context, TokoSayaActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    binding.btnToko.setOnClickListener {
                        val intent = Intent(context, BukaTokoActivity::class.java)
                        startActivity(intent)
                    }
                }

                if (user.isAdmin()) {
                    btnAdmin.visibility = View.VISIBLE
                } else {
                    btnAdmin.visibility = View.GONE
                }

                btnAdmin.setOnClickListener {
                    val intent = Intent(context, AdminPanelActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setUser()
    }
}