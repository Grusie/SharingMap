package com.gruise.data.datasource.searchmap

import com.gruise.data.model.SearchRegionsDto

interface SearchRegionDataSource {
    suspend fun getSearchRegionList(
        keyword: String
    ): Result<SearchRegionsDto>
}