package com.gruise.data.datasource

import com.gruise.data.dao.TagSearchDao
import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun getAllUserSearch(): Flow<List<LocalUserSearch>>
    suspend fun deleteAllUserSearch()
    suspend fun insertUserSearch(userSearch: LocalUserSearch)
    suspend fun getAllTagSearch(): Flow<List<LocalTagSearch>>
    suspend fun deleteAllTagSearch()
    suspend fun insertTagSearch(tagSearch: LocalTagSearch)

}