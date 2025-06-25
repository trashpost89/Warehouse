package com.example.warehouse.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class OrderItemWithProduct(
    @Embedded val orderItem: OrderItem,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product
) {
    val displayText: String
        get() = "${product.name} (${orderItem.box}): ${orderItem.quantity} шт."
}