package com.example.traveler.uis.home.profile

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.traveler.data.db.entities.User
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.uis.auth.AuthListener
import com.example.traveler.util.ApiException
import com.example.traveler.util.Coroutines
import com.example.traveler.util.NoInternetException

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    // LiveData for user data
    val user = repository.getUser()

    // AuthListener for UI feedback
    var authListener: AuthListener? = null

    // Fields for profile update
    var nic: String? = null
    var age: String? = null
    var name: String? = null

    init {
        // Observe the user LiveData for changes
        user.observeForever { newUser ->
            // Check if newUser is not null and has a nic value
            if (newUser != null) {
                nic = newUser.nic
            }
        }
    }

    // On update profile
    fun onProfileUpdateButtonClick(view: View){
        authListener?.onStarted()
        if (nic.isNullOrEmpty() || name.isNullOrEmpty() || age.isNullOrEmpty()) {
            authListener?.onFailure("Invalid input. Please check your details. ${age} and ${nic}")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository?.UserUpdate(name!!, age!!.toInt(),nic!!)
                authResponse.let {
                    val user = User(
                        id = it?.id,
                        nic = it?.nic,
                        isActive = it?.isActive,
                        isAdmin = it?.isAdmin,
                        lastLogin = it?.lastLogin,
                        name = it?.name,
                        age = it?.age
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