package com.example.warehouse.utils

import android.content.Context
import com.example.db.DatabaseHelper
import com.example.db.DatabaseHelper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

object CsvHandler {
    fun exportInventoryToCsv(context: Context, dbHelper: DatabaseHelper): Boolean {
        return try {
            val inventory = dbHelper.getAllInventory()
            val outputStream = context.openFileOutput("inventory_export.csv", Context.MODE_PRIVATE)
            val writer = OutputStreamWriter(outputStream)
            
            writer.write("Наименование,Коробка,Количество\n")
            inventory.forEach { item ->
                writer.write("${item.name},${item.box},${item.quantity}\n")
            }
            
            writer.close()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun importInventoryFromCsv(context: Context, dbHelper: DatabaseHelper): Boolean {
        return try {
            val inputStream = context.openFileInput("inventory_import.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))
            
            reader.readLine() // Пропускаем заголовок
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val parts = line!!.split(",")
                if (parts.size == 3) {
                    dbHelper.addInventoryItem(parts[0], parts[1], parts[2].toInt())
                }
            }
            
            reader.close()
            true
        } catch (e: Exception) {
            false
        }
    }
}