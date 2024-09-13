package com.gruise.domain.usecase.search

import com.gruise.domain.model.TagSearch
import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class InsertLocalTagSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(tagSearch: TagSearch): Result<Unit> {
        return searchRepository.insertLocalTagSearch(tagSearch)
    }

}