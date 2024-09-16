package com.gruise.domain.usecase.search

data class SearchUseCase(
    val getAllLocalUserSearchUseCase: GetAllLocalUserSearchUseCase,
    val deleteAllLocalUserSearchUseCase: DeleteAllLocalUserSearchUseCase,
    val insertLocalUserSearchUseCase: InsertLocalUserSearchUseCase,
    val getAllLocalTagSearchUseCase: GetAllLocalTagSearchUseCase,
    val deleteAllLocalTagSearchUseCase: DeleteAllLocalTagSearchUseCase,
    val insertLocalTagSearchUseCase: InsertLocalTagSearchUseCase,
    val getUserSearchUseCase: GetUserSearchUseCase
)
