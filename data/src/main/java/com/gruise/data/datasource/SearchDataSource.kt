package com.gruise.data.datasource

import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun getAllLocalUserSearch(): Flow<List<LocalUserSearch>>
    suspend fun deleteAllLocalUserSearch()
    suspend fun insertLocalUserSearch(userSearch: LocalUserSearch)
    suspend fun getAllLocalTagSearch(): Flow<List<LocalTagSearch>>
    suspend fun deleteAllLocalTagSearch()
    suspend fun insertLocalTagSearch(tagSearch: LocalTagSearch)

}