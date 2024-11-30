package com.gruise.data.datasource.archive

import com.gruise.data.model.ArchiveDto
import com.gruise.data.model.ArchivesDto
import com.gruise.data.service.ArchiveService
import com.gruise.domain.model.AdditionalArchiveModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
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

    override suspend fun getArchivesByAuthorId(authorId: Long): Result<ArchivesDto> {
        return archiveService.getArchivesByAuthorId(authorId)
    }

    override suspend fun getArchivesByStorageId(storageId: Long): Result<ArchivesDto> {
        return archiveService.getArchivesByStorageId(storageId)
    }

    override suspend fun saveArchive(
        additionalArchive: AdditionalArchiveModel,
        attachFileList: List<File?>
    ): Result<ArchiveDto> {
        return archiveService.saveArchive(
            address = createRequestBody(additionalArchive.address),
            name = createRequestBody(additionalArchive.placeName),
            content = createRequestBody(additionalArchive.content),
            positionX = createRequestBody(additionalArchive.longitude.toString()),
            positionY = createRequestBody(additionalArchive.latitude.toString()),
            tags = null,
            public = additionalArchive.isPublic,
            attaches = attachFileList.map { attachFile ->
                attachFile?.let {
                    createMultipartBody(
                        it,
                        "attaches"
                    )
                }
            }
        )
    }

    private fun createRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    private fun createMultipartBody(file: File, name: String): MultipartBody.Part {
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, file.name, requestFile)
    }
}