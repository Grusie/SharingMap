package com.gruise.domain.repository

import com.gruise.domain.model.Storage

interface StorageRepository {
    suspend fun getStorages(): Result<List<Storage>>
}