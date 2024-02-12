package com.fake.marketplace.presentation.screens.tabs.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.data.BackendException
import com.fake.marketplace.data.CachedDataException
import com.fake.marketplace.data.ParseBackendResponseException
import com.fake.marketplace.domain.entities.SortTypeEnum
import com.fake.marketplace.domain.useCases.product.GetCachedProductListUseCase
import com.fake.marketplace.domain.useCases.product.GetProductListUseCase
import com.fake.marketplace.domain.useCases.product.UpdateFavoriteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val getCachedProductListUseCase: GetCachedProductListUseCase,
    private val updateFavoriteProductUseCase: UpdateFavoriteProductUseCase
): ViewModel() {

    private val _state = MutableStateFlow<CatalogState>(CatalogState.Loading)
    val state = _state.asStateFlow()


    fun updateFavoriteState(id: String, isFavorite: Boolean) = viewModelScope.launch {
        updateFavoriteProductUseCase(id, isFavorite)
    }

    fun getProductList(tag: String, sortType: SortTypeEnum) = viewModelScope.launch {
        try {
            getProductListUseCase(tag, sortType).collect{
                _state.emit(CatalogState.ProductList(it))
            }
        } catch (e: ConnectException) {
            _state.emit(CatalogState.ErrorLoadingData("Нет интернет соединения"))
            getCachedProduct(tag, sortType)
        } catch (e: BackendException) {
            _state.emit(CatalogState.ErrorLoadingData("Ошибка на стороне сервера ${e.code}\n${e.message}"))
        } catch (e: ParseBackendResponseException) {
            _state.emit(CatalogState.ErrorLoadingData("Ошибка при чтении данных"))
        }
    }

    private fun getCachedProduct(tag: String, sortType: SortTypeEnum) = viewModelScope.launch {
        try {
            getCachedProductListUseCase(tag, sortType).collect {
                _state.emit(CatalogState.ProductList(it))
            }
        }  catch (e: CachedDataException) {
            _state.emit(CatalogState.ErrorLoadingData("Нет соединения с сервером.\nЛокальная база пуста"))
        } catch (e: IOException) {
            _state.emit(CatalogState.ErrorLoadingData("Произошла ошибка"))
        }
    }
}