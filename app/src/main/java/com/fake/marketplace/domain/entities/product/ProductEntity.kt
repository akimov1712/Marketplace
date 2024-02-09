package com.fake.marketplace.domain.entities.product

import com.fake.marketplace.data.source.locale.database.entities.product.FeedbackDbEntity
import com.fake.marketplace.data.source.locale.database.entities.product.PriceDbEntity

data class ProductEntity(
    val id: String,
    val title: String,
    val subTitle: String,
    val price: PriceEntity,
    val feedback: FeedbackEntity,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val infoList: List<InfoEntity>,
    val ingredients: String,
    val isFavorite: Boolean
)
