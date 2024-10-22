package com.gruise.data.datasource.archive

import android.util.Log
import com.gruise.data.model.ArchiveDto
import com.gruise.data.model.ArchivesDto
import com.gruise.data.service.ArchiveService
import javax.inject.Inject

class ArchiveRemoteDataSource @Inject constructor(
    private val archiveService: ArchiveService
) : ArchiveDataSource {
    override suspend fun getArchives(
        topLeftLatitude: Double?,
        topLeftLongitude: Double?,
        bottomRightLatitude: Double?,
        bottomRightLongitude: Double?,
        block: Boolean?,
        follow: Boolean?,
        tag: String?
    ): Result<ArchivesDto> {
        return archiveService.getArchives(
            topLeftLatitude,
            topLeftLongitude,
            bottomRightLatitude,
            bottomRightLongitude,
            block,
            follow,
            tag
        )
    }
}