package com.example.traveler.data.network.responses

import com.example.traveler.data.db.entities.Reservation


//array of reservation
data class ReservationResponse (
    val success : Boolean,
    val message : String,
    val data : List<Reservation>
)

//one item of reservation
data class OneReservationResponse (
    val success : Boolean,
    val message : String,
    val data : Reservation
)