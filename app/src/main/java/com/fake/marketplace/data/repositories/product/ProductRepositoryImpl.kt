package com.fake.marketplace.data.repositories.product

import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import com.fake.marketplace.domain.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(

): ProductRepository {

    override suspend fun getProductList(): Flow<List<ProductDbEntity>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteProductList(): Flow<List<ProductDbEntity>> {
        TODO("Not yet implemented")
    }

    override fun getProductItem(id: String): Flow<ProductDbEntity> {
        TODO("Not yet implemented")
    }
}