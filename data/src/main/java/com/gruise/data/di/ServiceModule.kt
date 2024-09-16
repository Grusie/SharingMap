package com.gruise.data.di

import com.gruise.data.service.SearchService
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
}