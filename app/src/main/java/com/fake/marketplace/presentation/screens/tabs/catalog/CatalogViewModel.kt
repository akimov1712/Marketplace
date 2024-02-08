package com.fake.marketplace.presentation.screens.tabs.catalog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.Const.SHOW_ALL_TAG
import com.fake.marketplace.domain.entities.SortedTypeEnum
import com.fake.marketplace.domain.entities.product.ProductEntity
import com.fake.marketplace.domain.useCases.product.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
): ViewModel() {

    private val _state = MutableStateFlow<CatalogState>(CatalogState.Loading)
    val state = _state.asStateFlow()

    private var fullList: List<ProductEntity> = emptyList()

    fun getProductList(tag: String, sortType: SortedTypeEnum) = viewModelScope.launch {
        getProductListUseCase().collect{
            fullList = it
            sortedProductList(tag = tag, sortType = sortType)
        }
    }

    fun sortedProductList(
        tag: String,
        sortType: SortedTypeEnum,
    ) {
        var sortedList = when (sortType) {
            SortedTypeEnum.POPULARITY_SORTED_TYPE -> fullList.sortedByDescending { it.feedback.rating }
            SortedTypeEnum.PRICE_ASK_SORTED_TYPE -> fullList.sortedBy { it.price.priceWithDiscount.toInt() }
            SortedTypeEnum.PRICE_DESK_SORTED_TYPE -> fullList.sortedByDescending { it.price.priceWithDiscount.toInt() }
        }
        if (tag != SHOW_ALL_TAG){
            sortedList = sortedList.filter {
                it.tags.contains(tag)
            }
        }
        _state.value = CatalogState.ProductList(sortedList)
    }

}