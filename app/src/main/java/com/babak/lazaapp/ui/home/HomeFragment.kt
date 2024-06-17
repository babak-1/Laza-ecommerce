package com.babak.lazaapp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.babak.lazaapp.R
import com.babak.lazaapp.base.BaseFragment
import com.babak.lazaapp.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            logout()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }

    private fun logout(){
        val sp = requireActivity().getSharedPreferences("ecommerce_local",Context.MODE_PRIVATE)
        sp.edit().putBoolean("isAuth",false).apply()
    }
}