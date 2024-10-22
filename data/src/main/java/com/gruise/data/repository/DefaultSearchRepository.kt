package com.gruise.data.repository

import com.gruise.data.datasource.search.SearchDataSource
import com.gruise.data.mapper.toDomain
import com.gruise.data.mapper.toLocalData
import com.gruise.domain.model.Tag
import com.gruise.domain.model.User
import com.gruise.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getAllLocalUserSearch(): Flow<Result<List<User>>> {
        return flow {
            try {
                searchDataSource.getAllLocalUserSearch().collect {
                    emit(Result.success(it.map { it.toDomain() }))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun deleteAllLocalUserSearch(): Result<Unit> {
        return try {
            searchDataSource.deleteAllLocalUserSearch()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertLocalUserSearch(user: User): Result<Unit> {
        return try {
            searchDataSource.insertLocalUserSearch(user.toLocalData())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllLocalTagSearch(): Flow<Result<List<Tag>>> {
        return flow  {
            try {
                searchDataSource.getAllLocalTagSearch().collect {
                    emit(Result.success(it.map { it.toDomain() }))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun deleteAllLocalTagSearch(): Result<Unit> {
        return try {
            searchDataSource.deleteAllLocalTagSearch()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertLocalTagSearch(tag: Tag): Result<Unit> {
        return try {
            searchDataSource.insertLocalTagSearch(tag.toLocalData())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserSearch(query: String, limit: Int): Result<List<User>> {
        return searchDataSource.getUserSearch(query, limit).map { it.map { it.toDomain() } }
    }

    override suspend fun getTagSearch(query: String, limit: Int): Result<List<Tag>> {
        return searchDataSource.getTagSearch(query, limit).map { it.map { it.toDomain() } }
    }
}