package com.example.warehouse

import android.app.Application
import com.example.warehouse.db.DatabaseHelper

class WarehouseApp : Application() {
    lateinit var dbHelper: DatabaseHelper

    override fun onCreate() {
        super.onCreate()
        dbHelper = DatabaseHelper(this)
    }
}