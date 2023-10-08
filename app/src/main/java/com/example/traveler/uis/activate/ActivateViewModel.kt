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
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.uis.auth.AuthListener

class ActivateViewModel(
    private val repository: UserRepository
):ViewModel() {

    // LiveData for user data
    val user = repository.getUser()
    var name : String? = null
    var age : String? = null
    var nic : String? = null
    var userType : String? = null
    var userGender : String? = null

    // AuthListener for UI feedback
    var authListener: AuthListener? = null

    var selectedGender = ObservableField<String>()


    fun onActivateButtonClick(view : View){
        println("test ${name} ${age} ${selectedGender}")
    }
}