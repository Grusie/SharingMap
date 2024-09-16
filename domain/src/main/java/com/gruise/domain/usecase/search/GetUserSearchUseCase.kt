package com.gruise.domain.usecase.search

import com.gruise.domain.model.User
import com.gruise.domain.repository.SearchRepository

class GetUserSearchUseCase(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String, limit: Int): Result<List<User>> {
        return searchRepository.getUserSearch(query, limit)
    }
}