package com.fake.marketplace.data.source.remote.entities

data class PriceDto(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)