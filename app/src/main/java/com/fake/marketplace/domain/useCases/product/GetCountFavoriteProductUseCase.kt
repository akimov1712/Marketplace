package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.repository.product.ProductRepository
import javax.inject.Inject

class GetCountFavoriteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke() = repository.getCountFavoriteProduct()

}