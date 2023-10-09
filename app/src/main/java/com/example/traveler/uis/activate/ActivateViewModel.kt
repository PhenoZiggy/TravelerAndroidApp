package com.example.traveler.uis.activate

import android.view.View
import android.widget.RadioGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveler.R
import com.example.traveler.data.db.entities.User
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.uis.auth.AuthListener
import com.example.traveler.util.ApiException
import com.example.traveler.util.Coroutines
import com.example.traveler.util.NoInternetException

class ActivateViewModel(
    private val repository: UserRepository
):ViewModel() {

    // LiveData for user data
    val user = repository.getUser()
    var name : String? = null
    var age : String? = null
    var userType : String? = null
    var userGender : String? = "Male"
    var nic: MutableLiveData<String?> = MutableLiveData(null)

    fun getLoggedUser() = repository.getUser()

    init {
        // Observe the user LiveData for changes
        user.observeForever { newUser ->
            // Check if newUser is not null and has a nic value
            if (newUser != null) {
                nic.postValue(newUser.nic)
                if(newUser.name.isNullOrEmpty()){
                    Coroutines.main {
                        try {
                            val res = repository.getUserByNic(newUser.nic!!)
                            res.data?.let {
                                val user = User(
                                    id = it.id,
                                    name = it.name,
                                    age = it.age,
                                    userGender = it.userGender,
                                    userType = it.userType
                                )
                                repository.updateUser(user.id!!, user.name!! , user.age!!, user.userGender!!,user.userType!!)
                                return@main
                            }
                        }catch(e:ApiException){

                        }
                    }
                }
            }
        }
    }

    // AuthListener for UI feedback
    var authListener: AuthListener? = null

    var selectedGender = ObservableField<String>()


    fun onActivateButtonClick(view : View){
       if(name.isNullOrEmpty()){
           authListener?.onFailure("Name cannot be blank")
           return
       }
        if(age.isNullOrEmpty()){
            authListener?.onFailure("Age cannot be blank")
            return
        }
        Coroutines.main {
            try {
                val userResponse = repository.CreateUser(name!! , age!!.toInt(), nic.value.toString() ,userGender!!)
                userResponse.data?.let {
                    val user = User(
                        id = it.id,
                        name = it.name,
                        age = it.age,
                        userGender = it.userGender,
                        userType = it.userType
                    )
                    authListener?.onSuccess(user)
                    repository.updateUser(user.id!!, user.name!! , user.age!!, user.userGender!!,user.userType!!)
                    return@main
                }
                authListener?.onFailure(userResponse?.message.toString())
            }catch (e : ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e : NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }

    }
}