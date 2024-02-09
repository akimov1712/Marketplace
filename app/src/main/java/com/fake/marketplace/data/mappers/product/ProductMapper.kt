package com.fake.marketplace.data.mappers.product

import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity
import com.fake.marketplace.data.source.remote.entities.ProductDto
import com.fake.marketplace.domain.entities.product.ProductEntity

object ProductMapper  {

    fun mapDtoToDb(dto: ProductDto) = ProductDbEntity(
        id = dto.id,
        title = dto.title,
        subTitle = dto.subtitle,
        price = PriceMapper.mapDtoToDb(dto.price),
        feedback = FeedbackMapper.mapDtoToDb(dto.feedback),
        tags = dto.tags,
        available = dto.available,
        description = dto.description,
        infoList = ProductInfoMapper.mapDtoListDbList(dto.info),
        ingredients = dto.ingredients,
        isFavorite = false
    )

    fun mapDbToEntity(dbEntity: ProductDbEntity) = ProductEntity(
        id = dbEntity.id,
        title = dbEntity.title,
        subTitle = dbEntity.subTitle,
        price = PriceMapper.mapDbToEntity(dbEntity.price),
        feedback = FeedbackMapper.mapDbToEntity(dbEntity.feedback),
        tags = dbEntity.tags,
        available = dbEntity.available,
        description = dbEntity.description,
        infoList = ProductInfoMapper.mapDbListEntityList(dbEntity.infoList),
        ingredients = dbEntity.ingredients,
        isFavorite = dbEntity.isFavorite
    )


    fun mapDtoListToDbList(list: List<ProductDto>) = list.map { mapDtoToDb(it) }

    fun mapDbListToEntityList(list: List<ProductDbEntity>) = list.map { mapDbToEntity(it) }

}