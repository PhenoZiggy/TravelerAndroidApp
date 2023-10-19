    package com.example.traveler.data.db.entities

    import androidx.room.Entity
    import androidx.room.PrimaryKey
    @Entity
    data class Train(
        @PrimaryKey(autoGenerate = false)
        val id: String,
        val trainName: String,
        val trainType: String,
        val startStation: String,
        val endStation: String,
        val startTime: String,
        val endTime: String,
        val price: Int,
        val seats: Int,
        val ownerNic: String,
        val isActive: Boolean
    )