package com.fake.marketplace.data.source.locale.database.entities.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class IdFavoriteProductDbEntity (
    @PrimaryKey
    val productId: String,
    val isFavorite: Boolean
)

