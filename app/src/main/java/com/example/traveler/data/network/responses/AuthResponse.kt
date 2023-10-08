package com.example.traveler.data.network.responses

import com.example.traveler.data.db.entities.User

//Response class
data class AuthResponse(
    val success : Boolean?,
    val message: String?,
    val data : User
)