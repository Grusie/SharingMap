package com.gruise.data.di

import com.gruise.data.service.ArchiveService
import com.gruise.data.service.MapService
import com.gruise.data.service.SearchRegionService
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
        @Qualifiers.DefaultRetrofit retrofit: Retrofit,
    ): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideArchiveService(
        @Qualifiers.DefaultRetrofit retrofit: Retrofit,
    ): ArchiveService {
        return retrofit.create(ArchiveService::class.java)
    }

    @Singleton
    @Provides
    fun provideMapService(
        @Qualifiers.NaverRetrofit retrofit: Retrofit
    ): MapService {
        return retrofit.create(MapService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchMapService(
        @Qualifiers.KakaoRetrofit retrofit: Retrofit
    ): SearchRegionService {
        return retrofit.create(SearchRegionService::class.java)
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