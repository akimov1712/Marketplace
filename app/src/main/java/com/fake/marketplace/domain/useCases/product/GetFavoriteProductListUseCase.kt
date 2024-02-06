package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.repository.product.ProductRepository
import javax.inject.Inject

class GetFavoriteProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke() = repository.getFavoriteProductList()

}