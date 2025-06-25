package com.example.warehouse.data.dao

import androidx.room.*
import com.example.warehouse.data.models.OrderItem

@Dao
interface OrderItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: OrderItem): Long

    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    suspend fun getItemsForOrder(orderId: Int): List<OrderItem>

    @Query("DELETE FROM order_items WHERE orderId = :orderId")
    suspend fun deleteItemsForOrder(orderId: Int)

    @Update
    suspend fun update(item: OrderItem)
}