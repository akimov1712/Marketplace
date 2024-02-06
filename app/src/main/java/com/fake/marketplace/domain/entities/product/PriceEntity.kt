package com.fake.marketplace.domain.entities.product

data class PriceEntity(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String,
)
