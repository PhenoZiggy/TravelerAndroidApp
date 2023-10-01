package com.example.traveler.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.traveler.data.db.entities.CURRENT_USER_ID
import com.example.traveler.data.db.entities.User
import com.example.traveler.data.network.responses.AuthResponse

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //to make assync use suspend
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user WHERE uid = ${CURRENT_USER_ID}")
    fun getUser():LiveData<User>

}