package com.example.warehouse.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithItems(
    @Embedded val order: Order,
    @Relation(
        entity = OrderItem::class,
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val items: List<OrderItemWithProduct>
)