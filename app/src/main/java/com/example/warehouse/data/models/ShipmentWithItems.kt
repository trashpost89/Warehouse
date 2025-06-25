package com.example.warehouse.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class ShipmentWithItems(
    @Embedded val shipment: Shipment,
    @Relation(
        parentColumn = "id",
        entityColumn = "shipmentId"
    )
    val items: List<ShipmentItem>
)