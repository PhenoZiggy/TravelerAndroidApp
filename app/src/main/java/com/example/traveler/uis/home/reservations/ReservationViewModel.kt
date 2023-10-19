package com.example.traveler.uis.home.reservations

import androidx.lifecycle.ViewModel
import com.example.traveler.data.repositories.ReservationRepository
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.util.lazyDeferred

class ReservationViewModel(
    repository: ReservationRepository,
    userRep : UserRepository
) : ViewModel() {
    val user = userRep.getUser()
    var nic: String? = null

    //initializing user data for get NIC
 init {
     user.observeForever { newUser ->
         // Check if newUser is not null and has a nic value
         if (newUser != null) {
             nic = newUser.nic
         }}
 }
    // get all reservations by NIC
    val reservation by lazyDeferred {
        repository.getReservations(nic!!)
    }
}