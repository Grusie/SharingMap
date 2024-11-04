package com.gruise.data.datasource.map

import com.gruise.data.BuildConfig
import com.gruise.data.model.ReverseGeoCodingDto
import com.gruise.data.service.MapService
import javax.inject.Inject

class MapRemoteDataSource @Inject constructor(
    private val mapService: MapService
) : MapDataSource {
    override suspend fun getAddress(
        latitude: Double,
        longitude: Double
    ): Result<ReverseGeoCodingDto> {
        return try {
            val response = mapService.getAddress(
                accessKey = BuildConfig.NAVER_ACCESS_KEY,
                secretKey = BuildConfig.NAVER_SECRET_KEY,
                coords = "$longitude,$latitude",
                output = "json"
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
