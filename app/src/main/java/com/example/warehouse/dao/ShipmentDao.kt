package com.example.warehouse.data.dao

import androidx.room.*
import com.example.warehouse.data.models.Shipment
import com.example.warehouse.data.models.ShipmentWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ShipmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shipment: Shipment): Long

    @Update
    suspend fun update(shipment: Shipment)

    @Transaction
    @Query("SELECT * FROM shipments ORDER BY date DESC")
    fun getAllShipmentsWithItems(): Flow<List<ShipmentWithItems>>

    @Transaction
    @Query("SELECT * FROM shipments WHERE id = :shipmentId")
    suspend fun getShipmentWithItems(shipmentId: Int): ShipmentWithItems?

    @Query("DELETE FROM shipments WHERE id = :shipmentId")
    suspend fun deleteShipment(shipmentId: Int)
}