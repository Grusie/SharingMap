package com.gruise.domain.usecase.search

import com.gruise.domain.model.UserSearch
import com.gruise.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUserSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(): Flow<Result<List<UserSearch>>> {
        return searchRepository.getAllUserSearch()
    }
}