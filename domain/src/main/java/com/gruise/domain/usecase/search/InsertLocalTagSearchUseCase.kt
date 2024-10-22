package com.gruise.domain.usecase.search

import com.gruise.domain.model.Tag
import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class InsertLocalTagSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(tag: Tag): Result<Unit> {
        return searchRepository.insertLocalTagSearch(tag)
    }

}