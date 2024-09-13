package com.gruise.domain.repository

import com.gruise.domain.model.TagSearch
import com.gruise.domain.model.UserSearch
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getAllLocalUserSearch(): Flow<Result<List<UserSearch>>>
    suspend fun deleteAllLocalUserSearch(): Result<Unit>
    suspend fun insertLocalUserSearch(userSearch: UserSearch): Result<Unit>
    suspend fun getAllLocalTagSearch(): Flow<Result<List<TagSearch>>>
    suspend fun deleteAllLocalTagSearch(): Result<Unit>
    suspend fun insertLocalTagSearch(tagSearch: TagSearch): Result<Unit>
}