package com.example.traveler.uis.home.reservations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.data.repositories.ReservationRepository
import com.example.traveler.data.repositories.UserRepository

class ReservationViewModelFactory(
    private val repository: ReservationRepository,
    private val userRep : UserRepository
):ViewModelProvider.NewInstanceFactory() {
    //reservation factory class to pass data to the view model
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReservationViewModel(repository , userRep) as T
    }
}