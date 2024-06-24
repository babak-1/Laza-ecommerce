package com.babak.lazaapp.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible(){
    this.visibility=View.VISIBLE
}

fun View.gone(){
    this.visibility=View.GONE
}

fun View.invisible(){
    this.visibility=View.INVISIBLE
}

fun ImageView.loadImageUrl(url:String){
    Glide.with(this).load(url).into(this)
}

