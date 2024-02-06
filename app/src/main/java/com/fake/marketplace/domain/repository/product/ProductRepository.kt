package com.fake.marketplace.domain.repository.product

import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import com.fake.marketplace.domain.entities.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductList(): Flow<List<ProductEntity>>
    fun getFavoriteProductList(): Flow<List<ProductEntity>>
    fun getProductItem(id: String): Flow<ProductEntity>
    fun getCachedProduct(): Flow<List<ProductEntity>>

}