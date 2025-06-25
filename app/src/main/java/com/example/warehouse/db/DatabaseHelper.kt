package com.example.warehouse.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.warehouse.db.entity.Order
import com.example.warehouse.db.entity.OrderItem
import com.example.warehouse.db.entity.OrderWithItems
import com.example.warehouse.db.entity.Product
import com.example.warehouse.db.entity.Shipment
import com.example.warehouse.db.entity.ShipmentItem
import com.example.warehouse.db.entity.ShipmentWithItems

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "inventory.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Product.CREATE_TABLE)
        db.execSQL(Order.CREATE_TABLE)
        db.execSQL(OrderItem.CREATE_TABLE)
        db.execSQL(Shipment.CREATE_TABLE)
        db.execSQL(ShipmentItem.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Product.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${Order.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${OrderItem.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${Shipment.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${ShipmentItem.TABLE_NAME}")
        onCreate(db)
    }

    // region Product operations
    fun insertProduct(product: Product): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(Product.COLUMN_NAME, product.name)
            put(Product.COLUMN_BOX, product.box)
            put(Product.COLUMN_QUANTITY, product.quantity)
        }
        val id = db.insert(Product.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getProducts(): List<Product> {
        val products = mutableListOf<Product>()
        val db = this.readableDatabase
        val cursor = db.query(
            Product.TABLE_NAME,
            null, null, null, null, null, null
        )

        cursor.use {
            while (it.moveToNext()) {
                products.add(Product.fromCursor(it))
            }
        }
        db.close()
        return products
    }

    fun getProductByName(name: String): Product? {
        val db = this.readableDatabase
        val cursor = db.query(
            Product.TABLE_NAME,
            null,
            "${Product.COLUMN_NAME} = ?",
            arrayOf(name),
            null, null, null
        )

        return cursor.use {
            if (it.moveToFirst()) {
                Product.fromCursor(it)
            } else {
                null
            }
        }.also { db.close() }
    }

    fun updateProductQuantity(name: String, newQuantity: Int): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(Product.COLUMN_QUANTITY, newQuantity)
        }
        val rowsAffected = db.update(
            Product.TABLE_NAME,
            values,
            "${Product.COLUMN_NAME} = ?",
            arrayOf(name)
        )
        db.close()
        return rowsAffected
    }
    // endregion

    // region Order operations
    fun insertOrder(order: Order): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(Order.COLUMN_NAME, order.name)
            put(Order.COLUMN_DATE, order.date)
        }
        val id = db.insert(Order.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun insertOrderItem(orderItem: OrderItem): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(OrderItem.COLUMN_ORDER_ID, orderItem.orderId)
            put(OrderItem.COLUMN_PRODUCT_NAME, orderItem.productName)
            put(OrderItem.COLUMN_BOX, orderItem.box)
            put(OrderItem.COLUMN_QUANTITY, orderItem.quantity)
        }
        val id = db.insert(OrderItem.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        val db = this.readableDatabase
        val cursor = db.query(
            Order.TABLE_NAME,
            null, null, null, null, null, null
        )

        cursor.use {
            while (it.moveToNext()) {
                orders.add(Order.fromCursor(it))
            }
        }
        db.close()
        return orders
    }

    fun getOrderWithItems(orderId: Long): OrderWithItems {
        val db = this.readableDatabase
        val orderCursor = db.query(
            Order.TABLE_NAME,
            null,
            "${Order.COLUMN_ID} = ?",
            arrayOf(orderId.toString()),
            null, null, null
        )

        val order = orderCursor.use {
            if (it.moveToFirst()) {
                Order.fromCursor(it)
            } else {
                throw IllegalArgumentException("Order not found")
            }
        }

        val itemsCursor = db.query(
            OrderItem.TABLE_NAME,
            null,
            "${OrderItem.COLUMN_ORDER_ID} = ?",
            arrayOf(orderId.toString()),
            null, null, null
        )

        val items = itemsCursor.use {
            val itemsList = mutableListOf<OrderItem>()
            while (it.moveToNext()) {
                itemsList.add(OrderItem.fromCursor(it))
            }
            itemsList
        }

        db.close()
        return OrderWithItems(order, items)
    }
    // endregion

    // region Shipment operations
    fun insertShipment(shipment: Shipment): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(Shipment.COLUMN_NAME, shipment.name)
            put(Shipment.COLUMN_DATE, shipment.date)
        }
        val id = db.insert(Shipment.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun insertShipmentItem(shipmentItem: ShipmentItem): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(ShipmentItem.COLUMN_SHIPMENT_ID, shipmentItem.shipmentId)
            put(ShipmentItem.COLUMN_PRODUCT_NAME, shipmentItem.productName)
            put(ShipmentItem.COLUMN_BOX, shipmentItem.box)
            put(ShipmentItem.COLUMN_QUANTITY, shipmentItem.quantity)
        }
        val id = db.insert(ShipmentItem.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getShipments(): List<Shipment> {
        val shipments = mutableListOf<Shipment>()
        val db = this.readableDatabase
        val cursor = db.query(
            Shipment.TABLE_NAME,
            null, null, null, null, null, null
        )

        cursor.use {
            while (it.moveToNext()) {
                shipments.add(Shipment.fromCursor(it))
            }
        }
        db.close()
        return shipments
    }

    fun getShipmentWithItems(shipmentId: Long): ShipmentWithItems {
        val db = this.readableDatabase
        val shipmentCursor = db.query(
            Shipment.TABLE_NAME,
            null,
            "${Shipment.COLUMN_ID} = ?",
            arrayOf(shipmentId.toString()),
            null, null, null
        )

        val shipment = shipmentCursor.use {
            if (it.moveToFirst()) {
                Shipment.fromCursor(it)
            } else {
                throw IllegalArgumentException("Shipment not found")
            }
        }

        val itemsCursor = db.query(
            ShipmentItem.TABLE_NAME,
            null,
            "${ShipmentItem.COLUMN_SHIPMENT_ID} = ?",
            arrayOf(shipmentId.toString()),
            null, null, null
        )

        val items = itemsCursor.use {
            val itemsList = mutableListOf<ShipmentItem>()
            while (it.moveToNext()) {
                itemsList.add(ShipmentItem.fromCursor(it))
            }
            itemsList
        }

        db.close()
        return ShipmentWithItems(shipment, items)
    }
    // endregion

    // region CSV operations
    fun importProductsFromCSV(lines: List<String>): Int {
        var importedCount = 0
        val db = this.writableDatabase
        try {
            db.beginTransaction()

            for (line in lines.drop(1)) { // Skip header
                val values = line.split(",")
                if (values.size >= 3) {
                    val product = Product(
                        name = values[0].trim(),
                        box = values[1].trim(),
                        quantity = values[2].trim().toIntOrNull() ?: 0
                    )

                    val existingProduct = getProductByName(product.name)
                    if (existingProduct != null) {
                        updateProductQuantity(
                            product.name,
                            existingProduct.quantity + product.quantity
                        )
                    } else {
                        insertProduct(product)
                    }
                    importedCount++
                }
            }

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
            db.close()
        }
        return importedCount
    }

    fun exportProductsToCSV(): String {
        val products = getProducts()
        val csv = StringBuilder().apply {
            append("name,box,quantity\n")
            products.forEach { product ->
                append("${product.name},${product.box},${product.quantity}\n")
            }
        }
        return csv.toString()
    }
    // endregion
}