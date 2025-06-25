package com.example.warehouse.data.dao

import androidx.room.*
import com.example.warehouse.data.models.Order
import com.example.warehouse.data.models.OrderWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order): Long

    @Transaction
    @Query("SELECT * FROM orders ORDER BY date DESC")
    fun getAllOrdersWithItems(): Flow<List<OrderWithItems>>

    @Query("DELETE FROM orders WHERE id = :orderId")
    suspend fun deleteOrder(orderId: Int)

    @Transaction
    @Query("SELECT * FROM orders WHERE id = :orderId")
    suspend fun getOrderWithItems(orderId: Int): OrderWithItems?
}