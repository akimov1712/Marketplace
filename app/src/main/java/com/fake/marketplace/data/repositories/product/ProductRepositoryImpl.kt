package com.fake.marketplace.data.repositories.product

import android.util.Log
import com.fake.marketplace.data.CachedDataException
import com.fake.marketplace.data.mappers.product.ProductMapper
import com.fake.marketplace.data.source.locale.database.dao.ProductDao
import com.fake.marketplace.data.source.locale.database.entities.product.IdFavoriteProductDbEntity
import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import com.fake.marketplace.data.source.remote.BaseRetrofitSource
import com.fake.marketplace.data.source.remote.ProductApiService
import com.fake.marketplace.data.source.remote.entities.ProductResponse
import com.fake.marketplace.domain.entities.product.ProductEntity
import com.fake.marketplace.domain.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject



class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productApi: ProductApiService,
): ProductRepository, BaseRetrofitSource() {

    override suspend fun getCachedProduct() =
        productDao.getProductList().map {
            if (it.isEmpty()) throw CachedDataException()
            it.map { product -> ProductMapper.mapDbToEntity(product) }
        }

    override suspend fun updateFavoriteProduct(
        id: String,
        isFavorite: Boolean
    ) {
        val entity = IdFavoriteProductDbEntity(id, isFavorite)
        productDao.updateFavoriteProduct(entity)
    }

    override suspend fun getProductList() =
        wrapRetrofitExceptions {
            val productResponse = productApi.getProductList()
            returnDataFlow(productResponse)
        }

    private suspend fun returnDataFlow(productResponse: Response<ProductResponse>): Flow<List<ProductEntity>> {
        processData(productResponse)
        return getCachedProduct()
    }

    private suspend fun processData(productResponse: Response<ProductResponse>) {
        if (productResponse.isSuccessful) {
            productResponse.body()?.let {
                productDao.addProductList(
                    ProductMapper.mapDtoListToDbList(it.items)
                )
            }
        }
    }

    override suspend fun getFavoriteProductList() =
        productDao.getFavoriteProductList().map {
            ProductMapper.mapDbListToEntityList(it)
        }



    override fun getProductItem(id: String) =
        productDao.getProductItem(id).map {
            ProductMapper.mapDbToEntity(it)
        }

}