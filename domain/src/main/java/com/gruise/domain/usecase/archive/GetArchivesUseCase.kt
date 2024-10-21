package com.gruise.domain.usecase.archive

import com.gruise.domain.repository.ArchiveRepository
import javax.inject.Inject

class GetArchivesUseCase @Inject constructor(
    private val archiveRepository: ArchiveRepository
){
    suspend operator fun invoke(
        topLeftLatitude: Double? = null,
        topLeftLongitude: Double? = null,
        bottomRightLatitude: Double? = null,
        bottomRightLongitude: Double? = null,
        block: Boolean? = null,
        follow: Boolean? = null,
        tag: String? = null
    ) = archiveRepository.getArchives(
        topLeftLatitude,
        topLeftLongitude,
        bottomRightLatitude,
        bottomRightLongitude,
        block,
        follow,
        tag,
    )

}
