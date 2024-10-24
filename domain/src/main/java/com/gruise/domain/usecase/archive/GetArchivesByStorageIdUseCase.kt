package com.gruise.domain.usecase.archive

import com.gruise.domain.model.Archive
import com.gruise.domain.repository.ArchiveRepository
import javax.inject.Inject

class GetArchivesByStorageIdUseCase @Inject constructor(
    private val archiveRepository: ArchiveRepository
){
    suspend operator fun invoke(storageId: Long): Result<List<Archive>> {
        return archiveRepository.getArchivesByStorageId(storageId)
    }
}