package com.fake.marketplace.data.mappers.product

import com.fake.marketplace.data.source.locale.database.entities.product.InfoDbEntity
import com.fake.marketplace.data.source.remote.entities.InfoDto
import com.fake.marketplace.domain.entities.product.InfoEntity

object ProductInfoMapper {

    private fun mapDtoToDb(dto: InfoDto) = InfoDbEntity(
        title = dto.title,
        value = dto.value
    )

    private fun mapDbToEntity(dbEntity: InfoDbEntity) = InfoEntity(
        title = dbEntity.title,
        value = dbEntity.value
    )

    fun mapDtoListDbList(dtoList: List<InfoDto>) = dtoList.map { mapDtoToDb(it) }

    fun mapDbListEntityList(dbEntityList: List<InfoDbEntity>) = dbEntityList.map { mapDbToEntity(it) }

}