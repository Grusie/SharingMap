package com.gruise.data.datasource.storage

import com.gruise.data.model.StorageDto
import com.gruise.data.model.StoragesDto
import com.gruise.data.service.StorageService
import javax.inject.Inject

class StorageRemoteDataSource @Inject constructor(
    private val storageService: StorageService
): StorageDataSource {
    override suspend fun getStorages(): Result<StoragesDto> {
        return storageService.getStorages()
    }

}