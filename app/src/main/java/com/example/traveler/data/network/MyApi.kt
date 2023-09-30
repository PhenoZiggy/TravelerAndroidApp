package com.example.traveler.data.network

import com.example.traveler.data.network.responses.AuthResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.kodein.di.android.BuildConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyApi {
    @Headers("Content-Type: application/json") // Set the Content-Type header to JSON
    @POST("login")
    suspend fun userLogin(@Body requestBody: RequestBody): Response<AuthResponse>

    companion object{
        operator fun invoke() : MyApi{
            return  Retrofit.Builder()
                .baseUrl("https://ticket-reservation-sliit-1e11f68f93ee.herokuapp.com/api/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}