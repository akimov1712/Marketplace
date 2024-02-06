package com.fake.marketplace.data.mappers

import com.fake.marketplace.data.source.locale.database.entities.product.PriceDbEntity
import com.fake.marketplace.data.source.remote.entities.PriceDto
import com.fake.marketplace.domain.entities.product.PriceEntity

object PriceMapper {

    fun mapDtoToDb(dto: PriceDto) = PriceDbEntity(
        price = dto.price,
        discount = dto.discount,
        priceWithDiscount = dto.priceWithDiscount,
        unit = dto.unit
    )

    fun mapDbToEntity(dbEntity: PriceDbEntity) = PriceEntity(
        price = dbEntity.price,
        discount = dbEntity.discount,
        priceWithDiscount = dbEntity.priceWithDiscount,
        unit = dbEntity.unit
    )

}