package com.example.traveler.uis.auth

import com.example.traveler.data.network.responses.AuthResponse

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: AuthResponse?)
    fun onFailure(message : String)
}