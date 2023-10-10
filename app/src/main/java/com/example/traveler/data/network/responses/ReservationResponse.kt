package com.example.traveler.data.network.responses

import com.example.traveler.data.db.entities.Reservation

data class ReservationResponse (
    val success : Boolean,
    val message : String,
    val data : List<Reservation>
)