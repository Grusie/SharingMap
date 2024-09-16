package com.gruise.data.datasource

import com.gruise.data.dao.TagSearchDao
import com.gruise.data.dao.UserSearchDao
import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import com.gruise.data.model.TagDto
import com.gruise.data.model.UserDto
import com.gruise.data.service.SearchService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val userSearchDao: UserSearchDao,
    private val tagSearchDao: TagSearchDao,
    private val searchService: SearchService
): SearchDataSource {
    override suspend fun getAllLocalUserSearch(): Flow<List<LocalUserSearch>> {
        return userSearchDao.getAll()
    }

    override suspend fun deleteAllLocalUserSearch() {
        return userSearchDao.deleteAll()
    }

    override suspend fun insertLocalUserSearch(userSearch: LocalUserSearch) {
        return userSearchDao.insertUserSearch(userSearch)
    }

    override suspend fun getAllLocalTagSearch(): Flow<List<LocalTagSearch>> {
        return tagSearchDao.getAll()
    }

    override suspend fun deleteAllLocalTagSearch() {
        return tagSearchDao.deleteAll()
    }

    override suspend fun insertLocalTagSearch(tagSearch: LocalTagSearch) {
        return tagSearchDao.insertTagSearch(tagSearch)
    }

    override suspend fun getUserSearch(query: String, limit: Int): Result<List<UserDto>> {
        return searchService.getUserSearch(query, limit).map { it.users }
    }

    override suspend fun getTagSearch(query: String, limit: Int): Result<List<TagDto>> {
        return searchService.getTagSearch(query, limit).map { it.tags }
    }
}