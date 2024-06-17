package com.babak.lazaapp.source.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class Repo @Inject constructor(
    private val firebaseAuth: FirebaseAuth
){
    suspend fun loginUser(email:String,password:String)=firebaseAuth.signInWithEmailAndPassword(email,password).await()

    suspend fun registerUser(email:String,password: String)=firebaseAuth.createUserWithEmailAndPassword(email,password).await()
}

