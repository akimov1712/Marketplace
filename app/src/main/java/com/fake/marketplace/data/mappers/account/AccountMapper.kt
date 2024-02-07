package com.fake.marketplace.data.mappers.account

import com.fake.marketplace.data.source.locale.database.entities.account.AccountDbEntity
import com.fake.marketplace.domain.entities.account.AccountEntity

object AccountMapper {

    fun mapEntityToDb(entity: AccountEntity) = AccountDbEntity(
        name = entity.name,
        surname = entity.surname,
        numberPhone = entity.numberPhone,
    )

    fun mapDbToEntity(entity: AccountDbEntity) = AccountEntity(
        name = entity.name,
        surname = entity.surname,
        numberPhone = entity.numberPhone,
    )

}