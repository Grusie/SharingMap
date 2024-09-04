package com.gruise.domain.usecase.search

data class SearchUseCase(
    val getAllUserSearchUseCase: GetAllUserSearchUseCase,
    val deleteAllUserSearchUseCase: DeleteAllUserSearchUseCase,
    val insertUserSearchUseCase: InsertUserSearchUseCase,
    val getAllTagSearchUseCase: GetAllTagSearchUseCase,
    val deleteAllTagSearchUseCase: DeleteAllTagSearchUseCase,
    val insertTagSearchUseCase: InsertTagSearchUseCase,
)
