package com.gruise.domain.repository

import com.gruise.domain.model.Tag
import com.gruise.domain.model.User
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getAllLocalUserSearch(): Flow<Result<List<User>>>
    suspend fun deleteAllLocalUserSearch(): Result<Unit>
    suspend fun insertLocalUserSearch(user: User): Result<Unit>
    suspend fun getAllLocalTagSearch(): Flow<Result<List<Tag>>>
    suspend fun deleteAllLocalTagSearch(): Result<Unit>
    suspend fun insertLocalTagSearch(tag: Tag): Result<Unit>
    suspend fun getUserSearch(query: String, limit: Int): Result<List<User>>
    suspend fun getTagSearch(query: String, limit: Int): Result<List<Tag>>
}