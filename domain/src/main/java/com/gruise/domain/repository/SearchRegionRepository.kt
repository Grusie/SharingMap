package com.gruise.domain.repository

import com.gruise.domain.model.SearchRegion

interface SearchRegionRepository {
    suspend fun getSearchRegionList(keyword: String): Result<List<SearchRegion>>
    suspend fun saveSearchRegionHistory(searchRegion: SearchRegion): Result<Nothing?>
    suspend fun getSearchRegionHistory(): Result<List<SearchRegion>>
    suspend fun clearSearchRegionHistory(): Result<Nothing?>
}