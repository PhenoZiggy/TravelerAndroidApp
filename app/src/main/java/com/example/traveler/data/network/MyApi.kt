package com.example.traveler.data.network

import com.example.traveler.data.network.responses.AuthResponse
import com.example.traveler.data.network.responses.NetworkConnectionInterceptor
import com.example.traveler.data.network.responses.ReservationResponse
import com.example.traveler.data.network.responses.TrainResponse
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MyApi {
    @POST("auth/login")
    suspend fun userLogin(@Body requestBody: RequestBody): Response<AuthResponse>

    @POST("user")
    suspend fun createUser(@Body requestBody: RequestBody): Response<AuthResponse>
    @GET("USER/{nic}")
    suspend fun getUser(@Path("nic") nic: String) : Response<AuthResponse>

    @POST("auth/register")
    suspend fun userSignup(@Body requestBody: RequestBody): Response<AuthResponse>

    @PUT("user/{nic}")
    suspend fun userUpdate(@Path("nic") nic: String, @Body requestBody: RequestBody): Response<AuthResponse>

    @GET("Train")
    suspend fun getTrains() : Response<TrainResponse>

    @GET("Reservation/nic/{nic}")
    suspend fun getReservations(@Path("nic") nic: String) : Response<ReservationResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{
            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return  Retrofit.Builder()
                .client(okkHttpClient)
                .baseUrl("https://ticket-reservation-sliit-1e11f68f93ee.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}