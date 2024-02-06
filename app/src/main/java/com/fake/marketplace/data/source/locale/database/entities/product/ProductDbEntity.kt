package com.fake.marketplace.data.source.locale.database.entities.product

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductDbEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val subTitle: String,
    @Embedded(prefix = "priceInfo")
    val price: PriceDbEntity,
    @Embedded
    val feedback: FeedbackDbEntity,
    val tags: List<String>,
    val available: Int,
    val description: String,
    val infoList: List<InfoDbEntity>,
    val ingredients: String
)
