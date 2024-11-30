package com.gruise.domain.usecase.archive

import com.gruise.domain.model.AdditionalArchiveModel
import com.gruise.domain.repository.ArchiveRepository
import java.io.File
import javax.inject.Inject

class SaveArchiveUseCase @Inject constructor(private val archiveRepository: ArchiveRepository) {
    suspend operator fun invoke(
        additionalArchiveModel: AdditionalArchiveModel,
        attachFileList: List<File?>
    ) {
        archiveRepository.saveArchive(
            additionalArchive = additionalArchiveModel,
            attachFileList = attachFileList
        )
    }
}