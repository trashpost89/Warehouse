package com.example.warehouse.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.data.dao.ShipmentDao
import com.example.warehouse.data.dao.ShipmentItemDao
import com.example.warehouse.data.models.Shipment
import com.example.warehouse.data.models.ShipmentItem
import com.example.warehouse.data.models.ShipmentWithItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ShipmentViewModel @Inject constructor(
    private val shipmentDao: ShipmentDao,
    private val shipmentItemDao: ShipmentItemDao
) : ViewModel() {

    private val _shipments = MutableStateFlow<List<ShipmentWithItems>>(emptyList())
    val shipments: StateFlow<List<ShipmentWithItems>> = _shipments

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadShipments()
    }

    private fun loadShipments() {
        viewModelScope.launch {
            try {
                shipmentDao.getAllShipmentsWithItems().collectLatest {
                    _shipments.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки поставок: ${e.message}"
            }
        }
    }

    fun createNewShipment() {
        viewModelScope.launch {
            try {
                val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
                val shipmentName = "Поступление-$date"

                val newShipment = Shipment(
                    name = shipmentName,
                    date = date
                )

                val shipmentId = shipmentDao.insert(newShipment).toInt()
                _errorMessage.value = "Новая поставка создана (ID: $shipmentId)"
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка создания поставки: ${e.message}"
            }
        }
    }

    fun addItemToShipment(shipmentId: Int, item: ShipmentItem) {
        viewModelScope.launch {
            try {
                shipmentItemDao.insert(item.copy(shipmentId = shipmentId))
                loadShipments() // Обновляем список после добавления
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка добавления позиции: ${e.message}"
            }
        }
    }

    fun deleteShipment(shipmentId: Int) {
        viewModelScope.launch {
            try {
                shipmentItemDao.deleteItemsForShipment(shipmentId)
                shipmentDao.deleteShipment(shipmentId)
                loadShipments() // Обновляем список после удаления
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка удаления поставки: ${e.message}"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}