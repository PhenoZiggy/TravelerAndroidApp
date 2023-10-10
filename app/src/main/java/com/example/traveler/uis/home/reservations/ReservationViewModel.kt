package com.example.traveler.uis.home.reservations

import androidx.lifecycle.ViewModel
import com.example.traveler.data.repositories.ReservationRepository
import com.example.traveler.util.lazyDeferred

class ReservationViewModel(
    repository: ReservationRepository
) : ViewModel() {
    val reservation by lazyDeferred {
        repository.getReservations("971510528v")
    }
}