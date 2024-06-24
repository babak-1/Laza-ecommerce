package com.babak.lazaapp.model

import androidx.annotation.DrawableRes

data class BrandModel(
    val id:Int,
    @DrawableRes val image:Int,
    val title:String
)
