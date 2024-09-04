package com.gruise.domain.usecase.search

import com.gruise.domain.model.TagSearch
import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class InsertTagSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(tagSearch: TagSearch) {
        searchRepository.insertTagSearch(tagSearch)
    }

}