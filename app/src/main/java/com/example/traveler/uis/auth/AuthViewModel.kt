package com.example.traveler.uis.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.traveler.data.db.entities.User
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.util.ApiException
import com.example.traveler.util.Coroutines
import com.example.traveler.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun getLoggedUser() = repository.getUser()

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            // error message
            authListener?.onFailure("NIC and Password cannot be empty")
            return
        }
        // success input
//        val loginResponse = UserRepository().UserLogin(email!! , password!!)
//        authListener?.onSuccess(loginResponse)
        Coroutines.main {
            try {
                val authResponse = repository.UserLogin(email!! , password!!)

                authResponse.let {
                    val user = User(
                        id = it.id,
                        nic = it.nic,
                        isActive = it.isActive,
                        isAdmin = it.isAdmin,
                        lastLogin = it.lastLogin
                    )
                    authListener?.onSuccess(it)
                    repository.saveUser(user)
                    return@main
                }


                authListener?.onFailure(authResponse.toString())
            }catch (e : ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e : NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }

    }
}