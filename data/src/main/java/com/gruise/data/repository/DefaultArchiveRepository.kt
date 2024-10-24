package com.gruise.data.repository

import com.gruise.data.datasource.archive.ArchiveRemoteDataSource
import com.gruise.data.mapper.toDomain
import com.gruise.domain.model.Archive
import com.gruise.domain.repository.ArchiveRepository
import javax.inject.Inject

class DefaultArchiveRepository @Inject constructor(
    private val archiveRemoteDataSource: ArchiveRemoteDataSource
) : ArchiveRepository {
    override suspend fun getArchives(
        topLeftLatitude: Double?,
        topLeftLongitude: Double?,
        bottomRightLatitude: Double?,
        bottomRightLongitude: Double?,
        block: Boolean?,
        follow: Boolean?,
        tag: String?
    ): Result<List<Archive>> {
        archiveRemoteDataSource.getArchives(
            topLeftLatitude,
            topLeftLongitude,
            bottomRightLatitude,
            bottomRightLongitude,
            block,
            follow,
            tag
        ).onSuccess {
            return Result.success(it.archives.map { it.toDomain() })
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception("getArchives failed"))
    }

    override suspend fun getArchivesByAuthorId(authorId: Long): Result<List<Archive>> {
        archiveRemoteDataSource.getArchivesByAuthorId(authorId).onSuccess {
            return Result.success(it.archives.map { it.toDomain() })
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception("getArchivesByAuthorId failed"))
    }
}