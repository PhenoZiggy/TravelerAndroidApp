package com.example.traveler.data.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    foreignKeys = [ForeignKey(
        entity = Train::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("trainId"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Reservation(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val trainId: String,
    val userNic: String,
    val seats: Int
)


// join reservations and train entity
data class ResevationWithTrainDetails(
    @Embedded val reservation: Reservation, // Embed the Reservation details
    @Relation(
        parentColumn = "trainId",
        entityColumn = "id"
    )
    val train: Train // Include the Train details using a Relation
)

