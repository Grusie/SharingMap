package com.gruise.domain.repository

import com.gruise.domain.model.Archive

interface ArchiveRepository {
    suspend fun getArchives(
        topLeftLatitude: Double?,
        topLeftLongitude: Double?,
        bottomRightLatitude: Double?,
        bottomRightLongitude: Double?,
        block: Boolean?,
        follow: Boolean?,
        tag: String?
    ): Result<List<Archive>>

    suspend fun getArchivesByAuthorId(authorId: Long): Result<List<Archive>>
}