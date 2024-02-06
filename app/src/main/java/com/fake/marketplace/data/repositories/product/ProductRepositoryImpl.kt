package com.fake.marketplace.data.repositories.product

import com.fake.marketplace.data.CachedDataException
import com.fake.marketplace.data.mappers.ProductMapper
import com.fake.marketplace.data.source.locale.database.dao.ProductDao
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

    override fun getCachedProduct() = productDao.getProductList().map {
        it ?: throw CachedDataException() // студия не видит смысла в этой строке(
        it.map { product -> ProductMapper.mapDbToEntity(product) }
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

    override fun getFavoriteProductList() =
        productDao.getFavoriteProductList().map {
            ProductMapper.mapDbListToEntityList(it)
        }

    override fun getProductItem(id: String) =
        productDao.getProductItem(id).map {
            ProductMapper.mapDbToEntity(it)
        }

}