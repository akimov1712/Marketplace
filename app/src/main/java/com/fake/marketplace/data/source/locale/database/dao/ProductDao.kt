package com.fake.marketplace.data.source.locale.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fake.marketplace.data.source.locale.database.entities.product.IdFavoriteProductDbEntity
import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT *, favorite_products.isFavorite FROM products " +
            "LEFT JOIN favorite_products ON products.id = favorite_products.productId " +
            "WHERE tags LIKE '%' || :tag || '%' ORDER BY :sortBy ASC")
    fun getProductListAsc(tag: String, sortBy: String): Flow<List<ProductDbEntity>>

    @Query("SELECT *, favorite_products.isFavorite FROM products " +
            "LEFT JOIN favorite_products ON products.id = favorite_products.productId " +
            "WHERE tags LIKE '%' || :tag || '%' ORDER BY :sortBy DESC")
    fun getProductListDesc(tag: String, sortBy: String): Flow<List<ProductDbEntity>>

    @Query("SELECT *, favorite_products.isFavorite FROM products " +
            "LEFT JOIN favorite_products ON products.id = favorite_products.productId " +
            "WHERE favorite_products.isFavorite ='1'")
    fun getFavoriteProductList(): Flow<List<ProductDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavoriteProduct(entity: IdFavoriteProductDbEntity)

    @Query("SELECT * FROM products WHERE id=:id LIMIT 1")
    fun getProductItem(id: String): Flow<ProductDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductList(productList: List<ProductDbEntity>)

    @Query("SELECT count(*) FROM favorite_products WHERE isFavorite ='1'")
    suspend fun getCountFavoriteProduct(): Int

}