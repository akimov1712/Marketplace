package com.fake.marketplace.data.source.locale.database.entities.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountDbEntity(
    val name: String,
    val surname: String,
    val numberPhone: String,
    @PrimaryKey()
    val id: Int = 0
)
