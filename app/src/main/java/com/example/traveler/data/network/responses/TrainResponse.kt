package com.example.traveler.data.network.responses

import com.example.traveler.data.db.entities.Train

data class TrainResponse (
    val success : Boolean,
    val message : String,
    val data : List<Train>
)