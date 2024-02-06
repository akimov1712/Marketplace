package com.fake.marketplace.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.domain.useCases.product.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getProductList: GetProductListUseCase
): ViewModel() {

    suspend fun getProductList() =
        viewModelScope.async {
            getProductList.invoke()
        }.await()


    init {
        viewModelScope.launch {
            getProductList()
        }
    }

}