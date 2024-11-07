package com.gruise.domain.usecase.storage

import com.gruise.domain.repository.StorageRepository
import javax.inject.Inject

class GetStoragesUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke() = storageRepository.getStorages()
}