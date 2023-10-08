package com.example.traveler.uis.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.uis.activate.ActivateViewModel

class ActivateViewModelFactory(
    private val repository: UserRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivateViewModel(repository) as T
    }
}