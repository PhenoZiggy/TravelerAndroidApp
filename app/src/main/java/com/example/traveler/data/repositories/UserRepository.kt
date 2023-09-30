package com.example.traveler.data.repositories
import androidx.lifecycle.MutableLiveData
import com.example.traveler.data.network.MyApi
import com.example.traveler.data.network.responses.AuthResponse
import com.example.traveler.util.JSON_MEDIA_TYPE
import com.example.traveler.util.loginJsonObject
import okhttp3.RequestBody

import retrofit2.Response

class UserRepository {
    suspend fun UserLogin(email : String , password : String): Response<AuthResponse>{
        val jsonObject = loginJsonObject(email , password)
        val requestBody = RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString())
        return MyApi().userLogin(requestBody)
    }
}