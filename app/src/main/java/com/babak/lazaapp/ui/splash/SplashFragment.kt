package com.babak.lazaapp.ui.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.babak.lazaapp.R
import com.babak.lazaapp.base.BaseFragment
import com.babak.lazaapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val isAuth = getAuth()
            if (isAuth) {
                delay(3000)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                delay(3000)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }

        }
    }

    private fun getAuth(): Boolean {
        val sp = requireActivity().getSharedPreferences("ecommerce_local", Context.MODE_PRIVATE)
        return sp.getBoolean("isAuth", false)

    }
}