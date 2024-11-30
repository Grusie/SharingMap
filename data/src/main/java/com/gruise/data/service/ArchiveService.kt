package com.gruise.data.service

import com.gruise.data.model.ArchiveDto
import com.gruise.data.model.ArchivesDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ArchiveService {
    @GET("api/archives")
    suspend fun getArchives(
        @Query("topLeftLatitude") topLeftLatitude: Double?,
        @Query("topLeftLongitude") topLeftLongitude: Double?,
        @Query("bottomRightLatitude") bottomRightLatitude: Double?,
        @Query("bottomRightLongitude") bottomRightLongitude: Double?,
        @Query("block") block: Boolean?,
        @Query("follow") follow: Boolean?,
        @Query("tag") tag: String?,
    ): Result<ArchivesDto>

    @GET("api/users/{authorId}/archives")
    suspend fun getArchivesByAuthorId(
        @Path("authorId") authorId: Long,
    ): Result<ArchivesDto>

    @GET("api/folders/{foldersId}/archives")
    suspend fun getArchivesByStorageId(
        @Path("foldersId") storageId: Long,
    ): Result<ArchivesDto>

    @Multipart
    @POST("api/archives")
    suspend fun saveArchive(
        @Part("address") address: RequestBody,
        @Part("name") name: RequestBody?,
        @Part("content") content: RequestBody?,
        @Part("position_x") positionX: RequestBody,
        @Part("position_y") positionY: RequestBody,
        @Part tags: List<MultipartBody.Part?>?,
        @Part("public") public: Boolean,
        @Part attaches: List<MultipartBody.Part?>?
    ): Result<ArchiveDto>
}