package com.gruise.domain.usecase.map

import com.gruise.domain.model.SearchRegion
import com.gruise.domain.repository.SearchRegionRepository
import javax.inject.Inject

class GetSearchRegionListUseCase @Inject constructor(private val searchRegionRepository: SearchRegionRepository) {
    suspend operator fun invoke(keyword: String): Result<List<SearchRegion>> {
        return searchRegionRepository.getSearchRegionList(keyword)
    }
}