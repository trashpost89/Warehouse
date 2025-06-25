package com.example.warehouse.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shipments")
data class Shipment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val date: String
)