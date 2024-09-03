package com.gruise.data.datasource

import com.gruise.data.dao.UserSearchDao
import com.gruise.data.model.LocalUserSearch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val userSearchDao: UserSearchDao,
): SearchDataSource {
    override suspend fun getAllUserSearch(): Flow<List<LocalUserSearch>> {
        return userSearchDao.getAll()
    }

    override suspend fun deleteAllUserSearch() {
        return userSearchDao.deleteAll()
    }

    override suspend fun insertUserSearch(userSearch: LocalUserSearch) {
        return userSearchDao.insertUserSearch(userSearch)
    }
}