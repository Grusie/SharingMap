package com.gruise.domain.usecase.archive

import com.gruise.domain.model.Archive
import com.gruise.domain.repository.ArchiveRepository
import javax.inject.Inject

class GetArchivesByAuthorIdUseCase @Inject constructor(
    private val archiveRepository: ArchiveRepository
) {
    suspend operator fun invoke(authorId: Long): Result<List<Archive>> {
        return archiveRepository.getArchivesByAuthorId(authorId)
    }
}