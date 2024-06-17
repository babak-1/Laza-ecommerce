package com.babak.lazaapp.ui.register

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.babak.lazaapp.R
import com.babak.lazaapp.base.BaseFragment
import com.babak.lazaapp.databinding.FragmentRegisterBinding
import com.babak.lazaapp.utils.gone
import com.babak.lazaapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.textViewRegister.setOnClickListener {
            registerUser()
        }

        binding.toLoginBtn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }


    private fun registerUser(){
        val email=binding.registerEmail.text.toString().trim()
        val password = binding.registerPassword.text.toString().trim()
        val rePassword = binding.registerRepassword.text.toString().trim()

        if(email.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty()){
            if (password==rePassword){
                binding.rePasswordTextField.error=null
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.emailTextField.error = null
                    viewModel.registerUser(email, password)
                }else {
                    Toast.makeText(context,"Email formatinda olmalidir",Toast.LENGTH_LONG).show()
                    binding.emailTextField.error ="Invalid email format"
                }
                viewModel.registerUser(email, password)
            }else{

                Toast.makeText(context,"Password does not match",Toast.LENGTH_LONG).show()
                binding.rePasswordTextField.error="Passowrd does not match"
            }

        }else{
           Toast.makeText(context,"Fields cannot be empty",Toast.LENGTH_LONG).show()
        }
    }

    private fun observeData(){
        viewModel.succes.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context,"Succesfuly",Toast.LENGTH_LONG).show()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }else{
                Toast.makeText(context,"Wrong attempt",Toast.LENGTH_LONG).show()

            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            if(it) binding.animationView.visible() else binding.animationView.gone()
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        }
    }
}