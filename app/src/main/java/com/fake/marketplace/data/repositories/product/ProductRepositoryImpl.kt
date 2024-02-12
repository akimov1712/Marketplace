package com.fake.marketplace.data.repositories.product

import com.fake.marketplace.data.CachedDataException
import com.fake.marketplace.data.mappers.product.ProductMapper
import com.fake.marketplace.data.source.locale.database.dao.ProductDao
import com.fake.marketplace.data.source.locale.database.entities.product.IdFavoriteProductDbEntity
import com.fake.marketplace.data.source.remote.BaseRetrofitSource
import com.fake.marketplace.data.source.remote.ProductApiService
import com.fake.marketplace.data.source.remote.entities.ProductResponse
import com.fake.marketplace.domain.entities.SortTypeEnum
import com.fake.marketplace.domain.entities.product.ProductEntity
import com.fake.marketplace.domain.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productApi: ProductApiService,
) : ProductRepository, BaseRetrofitSource() {

    override suspend fun getCountFavoriteProduct(): Int {
        val count = productDao.getCountFavoriteProduct()
        return count
    }

    override suspend fun getCachedProduct(
        tag: String,
        sortType: SortTypeEnum,
    ): Flow<List<ProductEntity>> {
        return when(sortType){
            SortTypeEnum.POPULARITY -> {
                productDao.getProductListSortedPopularityDesc(tag).map {
                    if (it.isEmpty()) throw CachedDataException()
                    it.map { product -> ProductMapper.mapDbToEntity(product) }
                }
            }
            SortTypeEnum.PRICE_ASC -> {
                productDao.getProductListSortedPriceAsc(tag).map {
                    if (it.isEmpty()) throw CachedDataException()
                    it.map { product -> ProductMapper.mapDbToEntity(product) }
                }
            }
            SortTypeEnum.PRICE_DESC -> {
                productDao.getProductListSortedPriceDesc(tag).map {
                    if (it.isEmpty()) throw CachedDataException()
                    it.map { product -> ProductMapper.mapDbToEntity(product) }
                }
            }
        }
    }


    override suspend fun updateFavoriteProduct(
        id: String,
        isFavorite: Boolean
    ) {
        val entity = IdFavoriteProductDbEntity(id, isFavorite)
        productDao.updateFavoriteProduct(entity)
    }

    override suspend fun getProductList(
        tag: String,
        sortType: SortTypeEnum,
    ): Flow<List<ProductEntity>>{
        return wrapRetrofitExceptions {
            val productResponse = productApi.getProductList()
            returnDataFlow(tag, sortType, productResponse)
        }
    }

    private suspend fun returnDataFlow(
        tag: String,
        sortType: SortTypeEnum,
        productResponse: Response<ProductResponse>): Flow<List<ProductEntity>> {
        processData(productResponse)
        return getCachedProduct(tag, sortType)
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