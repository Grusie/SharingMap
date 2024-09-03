package com.gruise.data.datasource

import com.gruise.data.model.LocalUserSearch
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun getAllUserSearch(): Flow<List<LocalUserSearch>>
    suspend fun deleteAllUserSearch()
    suspend fun insertUserSearch(userSearch: LocalUserSearch)
}