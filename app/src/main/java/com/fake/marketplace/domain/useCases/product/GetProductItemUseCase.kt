package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.repository.product.ProductRepository
import javax.inject.Inject

class GetProductItemUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(id: String) = repository.getProductItem(id)

}