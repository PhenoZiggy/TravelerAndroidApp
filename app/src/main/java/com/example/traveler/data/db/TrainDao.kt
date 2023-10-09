package com.example.traveler.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.traveler.data.db.entities.Train

@Dao
interface TrainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTrains(train : List<Train>)

    @Query("SELECT * FROM Train")
    fun getTrains() : LiveData<List<Train>>
}