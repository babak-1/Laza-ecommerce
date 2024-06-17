package com.babak.lazaapp.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babak.lazaapp.source.remote.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: Repo) :ViewModel() {
    var loading = MutableLiveData<Boolean>()
    var isSucces = MutableLiveData<Boolean>()
    fun loginUser(email:String,password:String){
        loading.value=true
        viewModelScope.launch {
            try {
                val response = repo.loginUser(email,password)
                Log.e("gelen",response.user?.email.toString())
                if (!response.user?.email.isNullOrEmpty()){
                    loading.value=false
                    isSucces.value=true
                }
            }catch (e:Exception){
                Log.e("Xetali",e.localizedMessage.toString())
                loading.value=false
                isSucces.value=false
            }
        }
    }
}