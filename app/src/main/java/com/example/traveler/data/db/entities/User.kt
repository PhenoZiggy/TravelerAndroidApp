package com.example.traveler.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    var id : Int? = null,
    var nic : String? = null,
    var isActive : Boolean = false,
    var isAdmin : Boolean = false,
    var lastLogin : String? = null,
    var message : String? = null
){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}