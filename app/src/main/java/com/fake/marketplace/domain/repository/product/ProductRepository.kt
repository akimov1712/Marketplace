package com.fake.marketplace.domain.repository.product

import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(): Flow<List<ProductDbEntity>>
    fun getFavoriteProductList(): Flow<List<ProductDbEntity>>
    fun getProductItem(id: String): Flow<ProductDbEntity>

}