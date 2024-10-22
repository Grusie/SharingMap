package com.gruise.data.datasource.search

import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import com.gruise.data.model.TagDto
import com.gruise.data.model.UserDto
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun getAllLocalUserSearch(): Flow<List<LocalUserSearch>>
    suspend fun deleteAllLocalUserSearch()
    suspend fun insertLocalUserSearch(userSearch: LocalUserSearch)
    suspend fun getAllLocalTagSearch(): Flow<List<LocalTagSearch>>
    suspend fun deleteAllLocalTagSearch()
    suspend fun insertLocalTagSearch(tagSearch: LocalTagSearch)
    suspend fun getUserSearch(query: String, limit: Int): Result<List<UserDto>>
    suspend fun getTagSearch(query: String, limit: Int): Result<List<TagDto>>

}