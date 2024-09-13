package com.gruise.domain.usecase.search

import com.gruise.domain.model.UserSearch
import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class InsertLocalUserSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(userSearch: UserSearch): Result<Unit> {
        return searchRepository.insertLocalUserSearch(userSearch)
    }
}