package com.babak.lazaapp.source.remote

import com.babak.lazaapp.model.ProductResponse
import com.babak.lazaapp.source.NetworkResource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import retrofit2.Response
import javax.inject.Inject

class Repo @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val productService: ProductService
){
    suspend fun loginUser(email:String,password:String)=firebaseAuth.signInWithEmailAndPassword(email,password).await()

    suspend fun registerUser(email:String,password: String)=firebaseAuth.createUserWithEmailAndPassword(email,password).await()


//    suspend fun getAllProducts(): Response<List<ProductResponse>> {
//        return productService.getAllProducts()
//    }

    suspend fun getAllProducts()=safeApiRequest {
        productService.getAllProducts()
    }

//    private suspend fun <T> safeApiRequest(request:suspend ()->T):NetworkResource<T>{
//       return try {
//            NetworkResource.Succes(request.invoke())
//        }catch (e:Exception){
//            NetworkResource.Error(e.localizedMessage.toString())
//        }
//    }

    private suspend fun <T> safeApiRequest(request:suspend()->Response<T>):NetworkResource<T>{
        return try {
            if (request.invoke().isSuccessful){
                NetworkResource.Succes(request().body())
            }else{
                NetworkResource.Error(request().errorBody().toString())
            }
        }catch (e:Exception){
            NetworkResource.Error(e.localizedMessage.toString())
        }
    }


}

