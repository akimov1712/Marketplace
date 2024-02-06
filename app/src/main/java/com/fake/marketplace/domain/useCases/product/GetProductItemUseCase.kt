package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductItemUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(id: String) = repository.getProductItem(id)

}