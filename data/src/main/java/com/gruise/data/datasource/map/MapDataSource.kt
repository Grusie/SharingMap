package com.gruise.data.datasource.map

import com.gruise.data.model.ReverseGeoCodingDto

interface MapDataSource {
    suspend fun getAddress(
        latitude: Double,
        longitude: Double
    ): Result<ReverseGeoCodingDto>
}