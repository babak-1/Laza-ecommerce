package com.babak.lazaapp.source

sealed class NetworkResource<T> (
    val data:T?=null,
    val message:String?=null
){
    class Succes<T>(data: T?):NetworkResource<T>(data)
    class Error<T>(message: String?):NetworkResource<T>(data = null,message)

}
