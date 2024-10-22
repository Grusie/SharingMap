package com.gruise.domain.usecase.search

import com.gruise.domain.model.User
import com.gruise.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocalUserSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(): Flow<Result<List<User>>> {
        return searchRepository.getAllLocalUserSearch()
    }
}