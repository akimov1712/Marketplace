package com.fake.marketplace.data.source.locale.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun getProductList(): Flow<List<ProductDbEntity>>

    @Query("SELECT * FROM products WHERE id IN (SELECT productId FROM favorite_products)")
    suspend fun getFavoriteProductList(): Flow<List<ProductDbEntity>>

    @Query("SELECT * FROM products WHERE id=:id LIMIT 1")
    suspend fun getProductItem(id: String): Flow<ProductDbEntity>

}