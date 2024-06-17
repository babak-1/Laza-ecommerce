package com.babak.lazaapp.ui.login

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.babak.lazaapp.R
import com.babak.lazaapp.base.BaseFragment
import com.babak.lazaapp.databinding.FragmentLoginBinding
import com.babak.lazaapp.utils.gone
import com.babak.lazaapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        val text = "By connecting your account, you confirm that you agree with our Terms and Conditions"
        val spannableString = SpannableString(text)

        // Define the start and end indices of the text you want to style
        val start = text.indexOf("Terms and Conditions")
        val end = start + "Terms and Conditions".length

        // Get the color from resources
        val color = ContextCompat.getColor(requireContext(), R.color.black) // Assuming you have blue color defined in your resources

        // Set the color span
        spannableString.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set the bold style span
        spannableString.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set the spannable string to the TextView
        binding.termText.text = spannableString

        observeData()
        binding.textViewLogin.setOnClickListener {
            login()

        }
    }

    private fun observeData(){
        viewModel.isSucces.observe(viewLifecycleOwner){
            if(it){
                if(binding.loginSwitch.isChecked){
                    setUserAuth()
                }
                Toast.makeText(context,"Ugurlu giris",Toast.LENGTH_LONG).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
            else{
                Toast.makeText(context,"Ugursuz cehd",Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                binding.animationView.visible()
            }else{
                binding.animationView.gone()
            }
        }
    }

    private fun login(){
        val email = binding.loginEmail.text.toString().trim()
        val password = binding.passwordLogin.text.toString().trim()

        if(email.isNotEmpty()&& password.isNotEmpty()){
            viewModel.loginUser(email,password)
        }else{
            Toast.makeText(context,"Bos olmaz",Toast.LENGTH_LONG).show()
        }
    }

    private fun setUserAuth(){
        val sp = requireActivity().getSharedPreferences("ecommerce_local",Context.MODE_PRIVATE)
        sp.edit().putBoolean("isAuth",true).apply()
    }
}