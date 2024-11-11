package com.gruise.data.datasource.searchmap

import com.gruise.data.model.SearchRegionsDto
import com.gruise.data.service.SearchRegionService
import javax.inject.Inject

class SearchRegionRemoteDataSource @Inject constructor(
    private val searchRegionService: SearchRegionService
) : SearchRegionDataSource {
    override suspend fun getSearchRegionList(keyword: String): Result<SearchRegionsDto> {
        return try {
            val response = searchRegionService.getSearchRegionList(
                keyword
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
