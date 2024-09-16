package com.gruise.data.di

import com.gruise.domain.repository.SearchRepository
import com.gruise.domain.usecase.search.DeleteAllLocalTagSearchUseCase
import com.gruise.domain.usecase.search.DeleteAllLocalUserSearchUseCase
import com.gruise.domain.usecase.search.GetAllLocalTagSearchUseCase
import com.gruise.domain.usecase.search.GetAllLocalUserSearchUseCase
import com.gruise.domain.usecase.search.GetTagSearchUseCase
import com.gruise.domain.usecase.search.GetUserSearchUseCase
import com.gruise.domain.usecase.search.InsertLocalTagSearchUseCase
import com.gruise.domain.usecase.search.InsertLocalUserSearchUseCase
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
            getAllLocalUserSearchUseCase = GetAllLocalUserSearchUseCase(searchRepository),
            deleteAllLocalUserSearchUseCase = DeleteAllLocalUserSearchUseCase(searchRepository),
            insertLocalUserSearchUseCase = InsertLocalUserSearchUseCase(searchRepository),
            getAllLocalTagSearchUseCase = GetAllLocalTagSearchUseCase(searchRepository),
            insertLocalTagSearchUseCase = InsertLocalTagSearchUseCase(searchRepository),
            deleteAllLocalTagSearchUseCase = DeleteAllLocalTagSearchUseCase(searchRepository),
            getUserSearchUseCase = GetUserSearchUseCase(searchRepository),
            getTagSearchUseCase = GetTagSearchUseCase(searchRepository)
        )
    }
}