package com.gruise.data.datasource

import com.gruise.data.dao.TagSearchDao
import com.gruise.data.dao.UserSearchDao
import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val userSearchDao: UserSearchDao,
    private val tagSearchDao: TagSearchDao,
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

    override suspend fun getAllTagSearch(): Flow<List<LocalTagSearch>> {
        return tagSearchDao.getAll()
    }

    override suspend fun deleteAllTagSearch() {
        return tagSearchDao.deleteAll()
    }

    override suspend fun insertTagSearch(tagSearch: LocalTagSearch) {
        return tagSearchDao.insertTagSearch(tagSearch)
    }
}