package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.entities.SortTypeEnum
import com.fake.marketplace.domain.repository.product.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(tag: String, sortType: SortTypeEnum)
    = repository.getProductList(tag, sortType)

}