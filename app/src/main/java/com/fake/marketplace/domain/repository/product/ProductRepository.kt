package com.fake.marketplace.domain.repository.product

import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(): Flow<List<ProductDbEntity>>
    suspend fun getFavoriteProductList(): Flow<List<ProductDbEntity>>
    suspend fun getProductItem(id: String): Flow<ProductDbEntity>

}