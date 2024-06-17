package com.babak.lazaapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babak.lazaapp.source.remote.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: Repo) :ViewModel() {
    var loading = MutableLiveData<Boolean>()
    var succes = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun registerUser(email:String,password:String){
        loading.value=true
        viewModelScope.launch {
            try {
                val response = repo.registerUser(email, password)
                if(!response.user?.email.isNullOrEmpty()){
                    loading.value=false
                    succes.value=true
                }
            }catch (e:Exception){
                loading.value=false
                succes.value=false
                error.value=e.localizedMessage.toString()

            }
        }
    }
}