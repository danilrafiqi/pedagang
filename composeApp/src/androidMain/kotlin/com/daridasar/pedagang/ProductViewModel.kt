package com.daridasar.pedagang

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daridasar.pedagang.entity.Product
import kotlinx.coroutines.launch

class ProductViewModel(private val sdk: ProductSDK) : ViewModel() {
    private val _state = mutableStateOf(ProductScreenState())
    val state: State<ProductScreenState> = _state

    fun loadProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, products = emptyList())
            try {
                val products = sdk.getProducts(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, products = products)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, products = emptyList())
            }
        }
    }
    init {
        loadProducts()
    }
}

data class ProductScreenState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList()
)