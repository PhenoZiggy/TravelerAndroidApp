package com.example.traveler.uis.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.util.Coroutines

class AuthViewModel : ViewModel() {
    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            // error message
            authListener?.onFailure("Email and Password cannot be empty")
            return
        }
        // success input
//        val loginResponse = UserRepository().UserLogin(email!! , password!!)
//        authListener?.onSuccess(loginResponse)
        Coroutines.main {
            val response = UserRepository().UserLogin(email!! , password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body())
        }else {
            authListener?.onFailure("Error Code ${response.code()}")
        } }

    }
}