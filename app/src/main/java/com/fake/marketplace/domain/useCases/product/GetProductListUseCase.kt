package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke() = repository.getProductList()

}