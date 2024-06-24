package com.babak.lazaapp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.babak.lazaapp.R
import com.babak.lazaapp.base.BaseFragment
import com.babak.lazaapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel by viewModels<HomeViewModel>()
    private val brandAdapter =BrandAdapter()
    private val productAdapter = ProductAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            logout()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
        binding.logoutIcon.setOnClickListener {
            logout()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
        observeData()

//        adapters
        binding.rvBand.adapter=brandAdapter
        binding.rvProduct.adapter = productAdapter
    }

    private fun logout(){
        val sp = requireActivity().getSharedPreferences("ecommerce_local",Context.MODE_PRIVATE)
        sp.edit().putBoolean("isAuth",false).apply()
    }

    private fun observeData(){
        viewModel.brandData.observe(viewLifecycleOwner){
            brandAdapter.updateList(it)
        }

        viewModel.data.observe(viewLifecycleOwner){
            productAdapter.updateList(it)
        }

        viewModel.error.observe(viewLifecycleOwner){

        }

        viewModel.loading.observe(viewLifecycleOwner){

        }
    }
}