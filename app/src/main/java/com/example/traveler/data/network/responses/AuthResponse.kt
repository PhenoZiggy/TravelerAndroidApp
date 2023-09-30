package com.example.traveler.data.network.responses

import com.example.traveler.data.db.entities.User

//Response class
data class AuthResponse(
    val isSuccess : Boolean?,
    val id: Int?,
    val nic: String?,
    val isActive: Boolean?,
    val isAdmin: Boolean?,
    val lastLogin: String?,
    val message: String?
)