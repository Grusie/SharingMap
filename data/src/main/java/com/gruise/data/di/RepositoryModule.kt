package com.gruise.data.di

import com.gruise.data.repository.DefaultArchiveRepository
import com.gruise.data.repository.DefaultMapRepository
import com.gruise.data.repository.DefaultSearchRepository
import com.gruise.domain.repository.ArchiveRepository
import com.gruise.domain.repository.MapRepository
import com.gruise.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: DefaultSearchRepository
    ): SearchRepository

    @Singleton
    @Binds
    abstract fun bindArchiveRepository(
        archiveRepositoryImpl: DefaultArchiveRepository
    ): ArchiveRepository

    @Singleton
    @Binds
    abstract fun bindMapRepository(
        mapRepositoryImpl: DefaultMapRepository
    ): MapRepository
}