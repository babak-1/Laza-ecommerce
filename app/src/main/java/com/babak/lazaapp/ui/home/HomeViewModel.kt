package com.babak.lazaapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babak.lazaapp.R
import com.babak.lazaapp.model.BrandModel
import com.babak.lazaapp.model.ProductResponse
import com.babak.lazaapp.source.NetworkResource
import com.babak.lazaapp.source.remote.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val brandData = MutableLiveData<List<BrandModel>>()
    val data = MutableLiveData<List<ProductResponse>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        createBrand()
        getProducts()
    }

    private fun createBrand() {
        val brandList = listOf(
            BrandModel(1, R.drawable.adidas_laza, "Adidas"),
            BrandModel(2, R.drawable.nike_laza, "Nike"),
            BrandModel(1, R.drawable.fila_laza, "Fila"),
            BrandModel(1, R.drawable.adidas_laza, "Adidas"),
            BrandModel(1, R.drawable.adidas_laza, "Adidas"),
        )
        brandData.value = brandList
    }

    private fun getProducts() {
        loading.value=true
        viewModelScope.launch {
            val res = repo.getAllProducts()

            when (res) {
                is NetworkResource.Succes -> {
                    res.data?.let {
                        data.value = it
                        loading.value=false
                    }
                }

                is NetworkResource.Error ->{
                    res.message?.let {
                        error.value=it
                        loading.value=false
                    }
                }
            }
        }
    }
}