package com.example.traveler.uis.home.reservations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.data.repositories.ReservationRepository

class ReservationViewModelFactory(
    private val repository: ReservationRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReservationViewModel(repository) as T
    }
}