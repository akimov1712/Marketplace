package com.fake.marketplace.presentation.screens.detailProduct

import com.fake.marketplace.domain.entities.product.ProductEntity

sealed class DetailProductState{

    data object Initial: DetailProductState()
    data class Product(val item: ProductEntity): DetailProductState()

}
