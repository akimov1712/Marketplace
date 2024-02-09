package com.fake.marketplace.domain.useCases.product

import com.fake.marketplace.domain.repository.product.ProductRepository
import javax.inject.Inject

class UpdateFavoriteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(id: String, isFavorite: Boolean) = repository.updateFavoriteProduct(id,isFavorite)

}