package com.fake.marketplace.presentation.screens.tabs.profile.favoriteProducts

import com.fake.marketplace.domain.entities.product.ProductEntity

sealed class FavoriteState {

    data object Loading: FavoriteState()
    data object ErrorProductListEmpty: FavoriteState()
    data class ProductList(val list: List<ProductEntity>): FavoriteState()

}