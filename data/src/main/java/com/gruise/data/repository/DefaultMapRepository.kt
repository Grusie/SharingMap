package com.gruise.data.repository

import com.gruise.data.datasource.map.MapDataSource
import com.gruise.domain.repository.MapRepository
import javax.inject.Inject

class DefaultMapRepository @Inject constructor(
    private val mapDataSource: MapDataSource
) : MapRepository {
    override suspend fun getAddress(latitude: Double, longitude: Double): Result<String> {
        return mapDataSource.getAddress(latitude, longitude).map {
            it.results?.let { response ->
                if (response.isNotEmpty()) {
                    val region = response.last().region
                    var result = ""

                    result += "${region.area1?.name ?: ""} "
                    result += "${region.area2?.name ?: ""} "
                    result += region.area3?.name ?: ""

                    result
                } else ""
            } ?: ""
        }
    }
}