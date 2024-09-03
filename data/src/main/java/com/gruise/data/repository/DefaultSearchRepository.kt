package com.gruise.data.repository

import com.gruise.data.datasource.SearchDataSource
import com.gruise.data.mapper.toDomain
import com.gruise.data.mapper.toLocalData
import com.gruise.domain.model.UserSearch
import com.gruise.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getAllUserSearch(): Flow<Result<List<UserSearch>>> {
        return flow {
            try {
                searchDataSource.getAllUserSearch().collect {
                    emit(Result.success(it.map { it.toDomain() }))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun deleteAllUserSearch(): Result<Unit> {
        return try {
            searchDataSource.deleteAllUserSearch()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertUserSearch(userSearch: UserSearch): Result<Unit> {
        return try {
            searchDataSource.insertUserSearch(userSearch.toLocalData())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}