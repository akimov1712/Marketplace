package com.fake.marketplace.domain.entities

data class PriceEntity(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String,
)
