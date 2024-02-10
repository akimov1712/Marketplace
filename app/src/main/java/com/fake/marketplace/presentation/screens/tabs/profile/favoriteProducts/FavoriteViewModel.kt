package com.fake.marketplace.presentation.screens.tabs.profile.favoriteProducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.domain.useCases.product.GetFavoriteProductListUseCase
import com.fake.marketplace.domain.useCases.product.UpdateFavoriteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteProductListUseCase: GetFavoriteProductListUseCase,
    private val updateFavoriteProductUseCase: UpdateFavoriteProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val state = _state.asStateFlow()

    fun updateFavoriteState(id: String, isFavorite: Boolean) = viewModelScope.launch {
        updateFavoriteProductUseCase(id, isFavorite)
    }

    fun getFavoriteProducts() = viewModelScope.launch {
        getFavoriteProductListUseCase().collect {
            if (it.isNotEmpty()) {
                _state.value = FavoriteState.ProductList(it)
            } else {
                _state.value = FavoriteState.ErrorProductListEmpty
            }
        }
    }

    init {
        getFavoriteProducts()
    }

}