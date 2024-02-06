package com.fake.marketplace.data.source.locale.entities.product

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fake.marketplace.domain.entities.product.InfoEntity

@Entity(tableName = "products")
data class ProductDbEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val subTitle: String,
    @Embedded
    val price: PriceDbEntity,
    @Embedded
    val feedback: FeedbackDbEntity,
    val tags: List<String>,
    val available: Int,
    val description: String,
    @Embedded
    val infoList: List<InfoEntity>,
    val ingredients: String
)
