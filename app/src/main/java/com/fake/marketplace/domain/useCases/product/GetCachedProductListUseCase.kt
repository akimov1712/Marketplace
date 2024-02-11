package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.entities.SortedTypeEnum
import com.fake.marketplace.domain.repository.product.ProductRepository
import javax.inject.Inject

class GetCachedProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(
        tag: String,
        sortType: SortedTypeEnum,
        ) = repository.getCachedProduct(tag, sortType)

}