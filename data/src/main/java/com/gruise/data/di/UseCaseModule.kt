package com.gruise.data.di

import com.gruise.domain.repository.SearchRepository
import com.gruise.domain.usecase.search.DeleteAllUserSearchUseCase
import com.gruise.domain.usecase.search.GetAllUserSearchUseCase
import com.gruise.domain.usecase.search.InsertUserSearchUseCase
import com.gruise.domain.usecase.search.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesSearchUseCase(
        searchRepository: SearchRepository
    ): SearchUseCase {
        return SearchUseCase(
            getAllUserSearchUseCase = GetAllUserSearchUseCase(searchRepository),
            deleteAllUserSearchUseCase = DeleteAllUserSearchUseCase(searchRepository),
            insertUserSearchUseCase = InsertUserSearchUseCase(searchRepository)
        )
    }
}