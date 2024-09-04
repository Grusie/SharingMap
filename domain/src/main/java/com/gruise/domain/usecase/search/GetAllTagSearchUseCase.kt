package com.gruise.domain.usecase.search

import com.gruise.domain.repository.SearchRepository
import javax.inject.Inject

class GetAllTagSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke() = searchRepository.getAllUserSearch()
}