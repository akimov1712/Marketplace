package com.fake.marketplace.data.source.locale.database.entities.product

data class PriceDbEntity(
    val price: String,
    val discount: Int,
    val priceWithDiscount: Int,
    val unit: String,
)
