package com.fake.marketplace.presentation.screens.detailProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.domain.useCases.product.GetProductItemUseCase
import com.fake.marketplace.domain.useCases.product.UpdateFavoriteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val getProductItemUseCase: GetProductItemUseCase,
    private val updateFavoriteProductUseCase: UpdateFavoriteProductUseCase
): ViewModel() {

    private val _state = MutableStateFlow<DetailProductState>(DetailProductState.Initial)
    val state = _state.asStateFlow()

    fun getProduct(id: String) = viewModelScope.launch {
        getProductItemUseCase(id).collect{
            _state.emit(DetailProductState.Product(it))
        }
    }

    fun updateFavorite(id: String, isFavorite: Boolean) = viewModelScope.launch {
        updateFavoriteProductUseCase(id, isFavorite)
    }

}