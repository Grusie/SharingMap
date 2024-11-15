package com.gruise.data.mapper

import com.gruise.data.model.SearchRegionDto
import com.gruise.data.model.SearchRegionsDto
import com.gruise.domain.model.SearchRegion

fun SearchRegionDto.toDomainModel(): SearchRegion{
    return SearchRegion(
        address = this.roadAddressName?.ifBlank { this.addressName }  ?: this.addressName,
        id = this.id?.toLong(),
        categoryName = this.categoryName,
        latitude = this.latitude?.toDouble(),
        longitude = this.longitude?.toDouble(),
        placeName = this.placeName
    )
}

fun SearchRegionsDto.toDomainModel(): List<SearchRegion> {
    return this.documents.map { it.toDomainModel() }
}