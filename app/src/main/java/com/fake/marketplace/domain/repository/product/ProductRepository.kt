package com.fake.marketplace.domain.repository.product

import com.fake.marketplace.domain.entities.SortedTypeEnum
import com.fake.marketplace.domain.entities.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(tag: String, sortType: SortedTypeEnum): Flow<List<ProductEntity>>
    suspend fun getFavoriteProductList(): Flow<List<ProductEntity>>
    suspend fun updateFavoriteProduct(id: String, isFavorite: Boolean)
    fun getProductItem(id: String): Flow<ProductEntity>
    suspend fun getCachedProduct(
        tag: String,
        sortType: SortedTypeEnum,
        ): Flow<List<ProductEntity>>
    suspend fun getCountFavoriteProduct(): Int

}