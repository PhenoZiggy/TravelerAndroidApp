package com.example.traveler.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reservation(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val trainId: String,
    val userNic: String,
    val seats: Int,
)