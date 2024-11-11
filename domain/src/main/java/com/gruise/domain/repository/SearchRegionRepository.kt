package com.gruise.domain.repository

import com.gruise.domain.model.SearchRegion

interface SearchRegionRepository {
    suspend fun getSearchRegionList(keyword: String): Result<List<SearchRegion>>
}