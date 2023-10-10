package com.example.traveler.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.traveler.data.db.entities.Reservation

@Dao
interface ReservationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllReservation(reservation : List<Reservation>)

    @Query("SELECT * FROM Reservation")
    fun getReservations() : LiveData<List<Reservation>>
}