package com.example.traveler.uis.home.profile

import androidx.lifecycle.ViewModel
import com.example.traveler.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
    val user = repository.getUser()
}