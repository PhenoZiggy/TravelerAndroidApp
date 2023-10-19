package com.example.traveler.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.traveler.data.db.entities.Reservation
import com.example.traveler.data.db.entities.ResevationWithTrainDetails

@Dao
interface ReservationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllReservation(reservation : List<Reservation>)


    //get all reservation data from reservation table
    @Query("SELECT * FROM Reservation")
    fun getReservations() : LiveData<List<ResevationWithTrainDetails>>
}