package com.gruise.data.di

import com.gruise.data.datasource.archive.ArchiveDataSource
import com.gruise.data.datasource.archive.ArchiveRemoteDataSource
import com.gruise.data.datasource.search.SearchDataSource
import com.gruise.data.datasource.search.SearchRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindSearchDataSource(
        searchRemoteDataSource: SearchRemoteDataSource,
    ): SearchDataSource

    @Singleton
    @Binds
    abstract fun bindArchiveDataSource(
        archiveRemoteDataSource: ArchiveRemoteDataSource
    ): ArchiveDataSource


}