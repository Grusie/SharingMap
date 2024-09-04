package com.gruise.domain.repository

import com.gruise.domain.model.TagSearch
import com.gruise.domain.model.UserSearch
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getAllUserSearch(): Flow<Result<List<UserSearch>>>
    suspend fun deleteAllUserSearch(): Result<Unit>
    suspend fun insertUserSearch(userSearch: UserSearch): Result<Unit>
    suspend fun getAllTagSearch(): Flow<Result<List<TagSearch>>>
    suspend fun deleteAllTagSearch(): Result<Unit>
    suspend fun insertTagSearch(tagSearch: TagSearch): Result<Unit>
}