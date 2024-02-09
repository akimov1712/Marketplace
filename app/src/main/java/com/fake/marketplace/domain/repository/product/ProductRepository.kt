package com.fake.marketplace.domain.repository.product

import com.fake.marketplace.domain.entities.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(): Flow<List<ProductEntity>>
    suspend fun getFavoriteProductList(): Flow<List<ProductEntity>>
    fun getProductItem(id: String): Flow<ProductEntity>
    suspend fun getCachedProduct(): Flow<List<ProductEntity>>

}