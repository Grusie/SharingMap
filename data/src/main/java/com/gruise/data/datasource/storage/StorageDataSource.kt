package com.gruise.data.datasource.storage

import com.gruise.data.model.StorageDto
import com.gruise.data.model.StoragesDto

interface StorageDataSource {
    suspend fun getStorages(): Result<StoragesDto>
}