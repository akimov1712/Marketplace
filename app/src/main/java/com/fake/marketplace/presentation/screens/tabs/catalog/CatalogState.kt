package com.fake.marketplace.presentation.screens.tabs.catalog

import com.fake.marketplace.domain.entities.product.ProductEntity

sealed class CatalogState {

    object Loading: CatalogState()
    data class ProductList(val list: List<ProductEntity>): CatalogState()

}