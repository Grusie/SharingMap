package com.gruise.domain.usecase.search

import com.gruise.domain.model.User
import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class InsertLocalUserSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return searchRepository.insertLocalUserSearch(user)
    }
}