package com.example.traveler.uis.auth

import com.example.traveler.data.db.entities.User
import com.example.traveler.data.network.responses.AuthResponse

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User?)
    fun onFailure(message : String)
}