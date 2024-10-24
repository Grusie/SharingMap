package com.gruise.data.service

import com.gruise.data.model.StoragesDto
import retrofit2.http.GET

interface StorageService {
    @GET("api/folders")
    suspend fun getStorages(): Result<StoragesDto>
}