package com.example.warehouse.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shipment_items",
    foreignKeys = [
        ForeignKey(
            entity = Shipment::class,
            parentColumns = ["id"],
            childColumns = ["shipmentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("shipmentId")]
)
data class ShipmentItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // Добавлено primary key
    val shipmentId: Int,
    val productId: Int,
    val productName: String,
    val box: String,
    val quantity: Int
)