package com.example.traveler.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.traveler.data.db.AppDatabase
import com.example.traveler.data.db.entities.Reservation
import com.example.traveler.data.db.entities.ResevationWithTrainDetails
import com.example.traveler.data.network.MyApi
import com.example.traveler.data.network.responses.SafeApiRequest
import com.example.traveler.util.Coroutines
import com.example.traveler.util.JSON_MEDIA_TYPE
import com.example.traveler.util.reserveObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class ReservationRepository (
    private val api : MyApi,
    private val db : AppDatabase
) : SafeApiRequest(){
    private val reservations = MutableLiveData<List<Reservation>>()

    init {
        reservations.observeForever {
            saveReservations(it)
        }
    }

    //reserve a train by calling API function
    suspend fun reserveAtrain(trainId: String, nic: String, seats: String) {
        val jsonObject = reserveObj(trainId , nic , seats)
        val requestBody = RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString())
        try {
            apiRequest { api.reservation(requestBody) }
        }catch (e: Exception){

        }
                //val reservationsData = response.data
                //reservations.postValue(reservationsData)
    }

    //get all reservations by calling API function
    suspend fun getReservations(nic: String) : LiveData<List<ResevationWithTrainDetails>>{
        return withContext(Dispatchers.IO){
            fetchReservations(nic)
            db.getReservationDao().getReservations()
        }
    }
    private suspend fun fetchReservations(nic : String) {
        if (isFetchNeeded()) {
            try {
                val response = apiRequest { api.getReservations(nic) }
                if (response.success) {
                    // Data fetched successfully
                    val reservationsData = response.data
                    reservations.postValue(reservationsData)
                } else {
                    // Handle the case where the network request was not successful
//                    networkErrors.postValue("Network request failed: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle exceptions related to network errors
//                networkErrors.postValue("Network error: ${e.message}")
            }
        }
    }
    private fun isFetchNeeded() : Boolean{
        return true
    }

    private fun saveReservations(reservation : List<Reservation>){
        Coroutines.io {
            db.getReservationDao().saveAllReservation(reservation)
        }
    }

}