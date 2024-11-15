package com.gruise.data.repository

import com.gruise.data.datasource.searchmap.SearchRegionDataSource
import com.gruise.data.mapper.toDomainModel
import com.gruise.domain.model.SearchRegion
import com.gruise.domain.repository.SearchRegionRepository
import javax.inject.Inject

class DefaultSearchRegionRepository @Inject constructor(
    private val searchRegionDataSource: SearchRegionDataSource
) : SearchRegionRepository {
    override suspend fun getSearchRegionList(keyword: String): Result<List<SearchRegion>> {
        return searchRegionDataSource.getSearchRegionList(keyword).map { it.toDomainModel() }
    }

    override suspend fun saveSearchRegionHistory(searchRegion: SearchRegion): Result<Nothing?> {
        return searchRegionDataSource.saveSearchRegionHistory(searchRegion)
    }

    override suspend fun getSearchRegionHistory(): Result<List<SearchRegion>> {
        return searchRegionDataSource.getSearchRegionHistory()
    }

    override suspend fun clearSearchRegionHistory(): Result<Nothing?> {
        return searchRegionDataSource.clearSearchRegionHistory()
    }

}