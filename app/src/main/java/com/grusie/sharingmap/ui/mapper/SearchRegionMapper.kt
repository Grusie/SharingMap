package com.grusie.sharingmap.ui.mapper

import com.gruise.domain.model.SearchRegion
import com.grusie.sharingmap.ui.model.SearchRegionUiModel

fun SearchRegion.toUiModel(): SearchRegionUiModel {
    return SearchRegionUiModel(
        id = id,
        latitude = latitude,
        longitude = longitude,
        address = address,
        placeName = placeName,
        categoryName = categoryName
    )
}