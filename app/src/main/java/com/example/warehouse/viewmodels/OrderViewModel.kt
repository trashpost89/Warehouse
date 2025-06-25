package com.example.warehouse.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.data.dao.OrderDao
import com.example.warehouse.data.models.Order
import com.example.warehouse.data.models.OrderWithItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderDao: OrderDao
) : ViewModel() {

    private val _allOrders: Flow<List<OrderWithItems>> = orderDao.getAllOrdersWithItems()
    val allOrders: Flow<List<OrderWithItems>> = _allOrders

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun createNewOrder(name: String, date: String) {
        viewModelScope.launch {
            try {
                orderDao.insert(Order(name = name, date = date))
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка создания заказа: ${e.message}"
            }
        }
    }

    fun deleteOrder(orderId: Int) {
        viewModelScope.launch {
            try {
                orderDao.deleteOrder(orderId)
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка удаления заказа: ${e.message}"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun generateOrderName(): String {
        val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        return "Заказ-$date"
    }
}