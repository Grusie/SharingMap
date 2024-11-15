package com.gruise.domain.usecase.map

import com.gruise.domain.model.SearchRegion
import com.gruise.domain.repository.SearchRegionRepository
import javax.inject.Inject

class SaveSearchRegionHistoryUseCase @Inject constructor(private val searchRegionRepository: SearchRegionRepository) {
    suspend operator fun invoke(searchRegion: SearchRegion): Result<Nothing?> {
        return searchRegionRepository.saveSearchRegionHistory(searchRegion)
    }
}