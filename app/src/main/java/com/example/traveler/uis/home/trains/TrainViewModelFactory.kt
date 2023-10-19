package com.example.traveler.uis.home.trains

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.data.repositories.ReservationRepository
import com.example.traveler.data.repositories.TrainRepository
import com.example.traveler.data.repositories.UserRepository

class TrainViewModelFactory(
    private val repository: TrainRepository,
    private val reseveRepo : ReservationRepository,
    private val userRepo: UserRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrainViewModel(repository , reseveRepo, userRepo) as T
    }
}