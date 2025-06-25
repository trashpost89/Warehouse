package com.example.warehouse.data.dao

import androidx.room.*
import com.example.warehouse.data.models.ShipmentItem

@Dao
interface ShipmentItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShipmentItem)

    @Query("SELECT * FROM shipment_items WHERE shipmentId = :shipmentId")
    suspend fun getItemsForShipment(shipmentId: Int): List<ShipmentItem>

    @Query("DELETE FROM shipment_items WHERE shipmentId = :shipmentId")
    suspend fun deleteItemsForShipment(shipmentId: Int)
}