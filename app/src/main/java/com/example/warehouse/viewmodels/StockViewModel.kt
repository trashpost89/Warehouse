package com.example.warehouse.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.data.dao.ProductDao
import com.example.warehouse.data.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val productDao: ProductDao
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadProducts() {
        viewModelScope.launch {
            try {
                productDao.getAllProducts().collect {
                    _products.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки: ${e.message}"
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}