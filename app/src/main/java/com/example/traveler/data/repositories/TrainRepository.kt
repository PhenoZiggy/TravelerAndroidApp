package com.example.traveler.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.traveler.data.db.AppDatabase
import com.example.traveler.data.db.entities.Train
import com.example.traveler.data.network.MyApi
import com.example.traveler.data.network.responses.SafeApiRequest
import com.example.traveler.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrainRepository (
    private val api : MyApi,
    private val db : AppDatabase
) : SafeApiRequest(){
    private val trains = MutableLiveData<List<Train>>()

    init {
        trains.observeForever {
            saveTrains(it)
        }
    }
    suspend fun getTrains() : LiveData<List<Train>>{
        return withContext(Dispatchers.IO){
            fetchTrain()
            db.getTrainDao().getTrains()
        }
    }
    private suspend fun fetchTrain() {
        if (isFetchNeeded()) {
            try {
                val response = apiRequest { api.getTrains() }

                if (response.success) {
                    // Data fetched successfully
                    val trainsData = response.data
                    trains.postValue(trainsData)
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

    private fun saveTrains(trains : List<Train>){
        Coroutines.io {
            db.getTrainDao().saveAllTrains(trains)
        }
    }

}