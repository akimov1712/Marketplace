package com.fake.marketplace.domain.repository.product

import com.fake.marketplace.domain.entities.SortTypeEnum
import com.fake.marketplace.domain.entities.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(tag: String, sortType: SortTypeEnum): Flow<List<ProductEntity>>
    suspend fun getFavoriteProductList(): Flow<List<ProductEntity>>
    suspend fun updateFavoriteProduct(id: String, isFavorite: Boolean)
    fun getProductItem(id: String): Flow<ProductEntity>
    suspend fun getCachedProduct(
        tag: String,
        sortType: SortTypeEnum,
        ): Flow<List<ProductEntity>>
    suspend fun getCountFavoriteProduct(): Int

}