package com.gruise.data.datasource.archive

import com.gruise.data.model.ArchiveDto
import com.gruise.data.model.ArchivesDto
import com.gruise.domain.model.AdditionalArchiveModel
import java.io.File

interface ArchiveDataSource {
    suspend fun getArchives(
        topLeftLatitude: Double?,
        topLeftLongitude: Double?,
        bottomRightLatitude: Double?,
        bottomRightLongitude: Double?,
        block: Boolean?,
        follow: Boolean?,
        tag: String?
    ): Result<ArchivesDto>

    suspend fun getArchivesByAuthorId(authorId: Long): Result<ArchivesDto>

    suspend fun getArchivesByStorageId(storageId: Long): Result<ArchivesDto>

    suspend fun saveArchive(
        additionalArchive: AdditionalArchiveModel,
        attachFileList: List<File?>
    ): Result<ArchiveDto>
}