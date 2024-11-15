package com.gruise.domain.usecase.map

import com.gruise.domain.repository.SearchRegionRepository
import javax.inject.Inject

class ClearSearchRegionHistoryUseCase @Inject constructor(private val searchRegionRepository: SearchRegionRepository) {
    suspend operator fun invoke(): Result<Nothing?> {
        return searchRegionRepository.clearSearchRegionHistory()
    }
}