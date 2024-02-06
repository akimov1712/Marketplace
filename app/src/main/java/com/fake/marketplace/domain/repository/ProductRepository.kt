package com.fake.marketplace.domain.repository

import com.fake.marketplace.domain.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(): Flow<List<ProductEntity>>
    suspend fun getFavoriteProductList(): Flow<List<ProductEntity>>
    suspend fun getProductItem(id: String): Flow<ProductEntity>

}