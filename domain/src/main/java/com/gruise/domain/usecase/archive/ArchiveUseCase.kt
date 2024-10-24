package com.gruise.domain.usecase.archive

data class ArchiveUseCase(
    val getArchivesUseCase: GetArchivesUseCase,
    val getArchivesByAuthorIdUseCase: GetArchivesByAuthorIdUseCase
)
