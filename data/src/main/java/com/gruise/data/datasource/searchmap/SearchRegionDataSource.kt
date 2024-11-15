package com.gruise.data.datasource.searchmap

import com.gruise.data.model.SearchRegionsDto
import com.gruise.domain.model.SearchRegion

interface SearchRegionDataSource {
    suspend fun getSearchRegionList(
        keyword: String
    ): Result<SearchRegionsDto>

    suspend fun saveSearchRegionHistory(
        searchRegion: SearchRegion
    ): Result<Nothing?>

    suspend fun getSearchRegionHistory(): Result<List<SearchRegion>>

    suspend fun clearSearchRegionHistory(): Result<Nothing?>
}