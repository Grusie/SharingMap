package com.gruise.data.di

import com.gruise.data.service.ArchiveService
import com.gruise.data.service.SearchService
import com.gruise.data.service.StorageService
import com.gruise.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {


    @Singleton
    @Provides
    fun provideSearchService(
        retrofit: Retrofit,
    ): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideArchiveService(
        retrofit: Retrofit,
    ): ArchiveService {
        return retrofit.create(ArchiveService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(
        retrofit: Retrofit,
    ): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideStorageService(
        retrofit: Retrofit,
    ): StorageService {
        return retrofit.create(StorageService::class.java)
    }
}