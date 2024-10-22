package com.gruise.domain.usecase.search

import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class DeleteAllLocalTagSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
){
    suspend operator fun invoke(): Result<Unit> {
        return searchRepository.deleteAllLocalTagSearch()
    }
}