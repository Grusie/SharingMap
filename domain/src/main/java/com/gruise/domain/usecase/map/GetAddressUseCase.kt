package com.gruise.domain.usecase.map

import com.gruise.domain.repository.MapRepository
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(private val mapRepository: MapRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Result<String> {
        return mapRepository.getAddress(latitude, longitude)
    }
}