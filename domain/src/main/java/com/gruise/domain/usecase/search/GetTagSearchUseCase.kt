package com.gruise.domain.usecase.search

import com.gruise.domain.model.Tag
import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class GetTagSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String, limit: Int): Result<List<Tag>> {
        return searchRepository.getTagSearch(query, limit)
    }
}