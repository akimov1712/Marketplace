package com.fake.marketplace.data.mappers.product

import com.fake.marketplace.data.source.locale.database.entities.product.FeedbackDbEntity
import com.fake.marketplace.data.source.remote.entities.FeedbackDto
import com.fake.marketplace.domain.entities.product.FeedbackEntity

object FeedbackMapper {

    fun mapDtoToDb(dto: FeedbackDto) = FeedbackDbEntity(
        count = dto.count,
        rating = dto.rating
    )

    fun mapDbToEntity(dbEntity: FeedbackDbEntity) = FeedbackEntity(
        count = dbEntity.count,
        rating = dbEntity.rating
    )

}