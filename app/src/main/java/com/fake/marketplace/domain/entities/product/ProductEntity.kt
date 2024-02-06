package com.fake.marketplace.domain.entities.product

import com.fake.marketplace.data.source.locale.entities.product.FeedbackDbEntity
import com.fake.marketplace.data.source.locale.entities.product.PriceDbEntity

data class ProductEntity(
    val id: String,
    val title: String,
    val subTitle: String,
    val price: PriceDbEntity,
    val feedback: FeedbackDbEntity,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val infoList: List<InfoEntity>,
    val ingredients: String
)
