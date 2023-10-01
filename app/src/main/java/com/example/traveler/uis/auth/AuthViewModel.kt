package com.example.traveler.uis.auth

import android.content.Intent
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
    var nic : String? = null
    var password : String? = null
    var passwordConfirm : String? = null
    var authListener : AuthListener? = null

    fun getLoggedUser() = repository.getUser()

    //Login Function
    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(nic.isNullOrEmpty() || password.isNullOrEmpty()){
            // error message
            authListener?.onFailure("NIC and Password cannot be empty")
            return
        }
        // success input
//        val loginResponse = UserRepository().UserLogin(email!! , password!!)
//        authListener?.onSuccess(loginResponse)
        Coroutines.main {
            try {
                val authResponse = repository.UserLogin(nic!! , password!!)

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

    //Navigate to Register
    fun onSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    //navigate to login
    fun onLogin(view: View){
        Intent(view.context, MainActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    //Register function
    fun onSignupButtonClick(view: View){
        authListener?.onStarted()
        if(nic.isNullOrEmpty()){
            authListener?.onFailure("NIC cannot be empty")
            return
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailure("Password cannot be empty")
            return
        }

        if(password != passwordConfirm){
            authListener?.onFailure("Confirm password did not match")
            return
        }
        // success input
//        val loginResponse = UserRepository().UserLogin(email!! , password!!)
//        authListener?.onSuccess(loginResponse)
        Coroutines.main {
            try {
                val authResponse = repository.UserSignup(nic!! , password!!)

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