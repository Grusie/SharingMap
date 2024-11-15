package com.gruise.data.di

import com.gruise.data.datasource.archive.ArchiveDataSource
import com.gruise.data.datasource.archive.ArchiveRemoteDataSource
import com.gruise.data.datasource.map.MapDataSource
import com.gruise.data.datasource.map.MapRemoteDataSource
import com.gruise.data.datasource.search.SearchDataSource
import com.gruise.data.datasource.search.SearchRemoteDataSource
import com.gruise.data.datasource.searchmap.SearchRegionDataSource
import com.gruise.data.datasource.searchmap.SearchRegionRemoteDataSource
import com.gruise.data.datasource.storage.StorageDataSource
import com.gruise.data.datasource.storage.StorageRemoteDataSource
import com.gruise.data.datasource.user.UserDataSource
import com.gruise.data.datasource.user.UserRemoteDataSource
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

    @Singleton
    @Binds
    abstract fun bindMapDataSource(
        mapRemoteDataSource: MapRemoteDataSource
    ): MapDataSource

    @Singleton
    @Binds
    abstract fun bindUserDataSource(
        userRemoteDataSource: UserRemoteDataSource
    ): UserDataSource

    @Singleton
    @Binds
    abstract fun bindStorageDataSource(
        storageRemoteDataSource: StorageRemoteDataSource
    ): StorageDataSource

    @Singleton
    @Binds
    abstract fun bindSearchRegionDataSource(
        searchRegionRemoteDataSource: SearchRegionRemoteDataSource,
    ): SearchRegionDataSource
}