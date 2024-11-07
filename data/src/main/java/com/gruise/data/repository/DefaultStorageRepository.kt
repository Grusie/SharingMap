package com.gruise.data.repository

import com.gruise.data.datasource.storage.StorageDataSource
import com.gruise.data.mapper.toDomain
import com.gruise.domain.model.Storage
import com.gruise.domain.repository.StorageRepository
import javax.inject.Inject

class DefaultStorageRepository @Inject constructor(
    private val storageDataSource: StorageDataSource,
): StorageRepository {
    override suspend fun getStorages(): Result<List<Storage>> {
        storageDataSource.getStorages().onSuccess {
            return Result.success(it.storages.map { it.toDomain() })
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception("getStorages failed"))
    }
}