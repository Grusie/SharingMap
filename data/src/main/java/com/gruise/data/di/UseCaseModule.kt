package com.gruise.data.di

import com.gruise.domain.repository.ArchiveRepository
import com.gruise.domain.repository.MapRepository
import com.gruise.domain.repository.SearchRegionRepository
import com.gruise.domain.repository.SearchRepository
import com.gruise.domain.repository.StorageRepository
import com.gruise.domain.repository.UserRepository
import com.gruise.domain.usecase.archive.ArchiveUseCase
import com.gruise.domain.usecase.archive.GetArchivesByAuthorIdUseCase
import com.gruise.domain.usecase.archive.GetArchivesByStorageIdUseCase
import com.gruise.domain.usecase.archive.GetArchivesUseCase
import com.gruise.domain.usecase.map.ClearSearchRegionHistoryUseCase
import com.gruise.domain.usecase.map.GetAddressUseCase
import com.gruise.domain.usecase.map.GetSearchRegionHistoryUseCase
import com.gruise.domain.usecase.map.GetSearchRegionListUseCase
import com.gruise.domain.usecase.map.MapUseCases
import com.gruise.domain.usecase.map.SaveSearchRegionHistoryUseCase
import com.gruise.domain.usecase.search.DeleteAllLocalTagSearchUseCase
import com.gruise.domain.usecase.search.DeleteAllLocalUserSearchUseCase
import com.gruise.domain.usecase.search.GetAllLocalTagSearchUseCase
import com.gruise.domain.usecase.search.GetAllLocalUserSearchUseCase
import com.gruise.domain.usecase.search.GetTagSearchUseCase
import com.gruise.domain.usecase.search.GetUserSearchUseCase
import com.gruise.domain.usecase.search.InsertLocalTagSearchUseCase
import com.gruise.domain.usecase.search.InsertLocalUserSearchUseCase
import com.gruise.domain.usecase.search.SearchUseCase
import com.gruise.domain.usecase.storage.GetStoragesUseCase
import com.gruise.domain.usecase.storage.StorageUseCase
import com.gruise.domain.usecase.user.GetMyInfoUseCase
import com.gruise.domain.usecase.user.GetUserByUserIdUseCase
import com.gruise.domain.usecase.user.UserUseCase
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

    @Singleton
    @Provides
    fun providesArchiveUseCase(
        archiveRepository: ArchiveRepository
    ): ArchiveUseCase {
        return ArchiveUseCase(
            getArchivesUseCase = GetArchivesUseCase(archiveRepository),
            getArchivesByAuthorIdUseCase = GetArchivesByAuthorIdUseCase(archiveRepository),
            getArchivesByStorageIdUseCase = GetArchivesByStorageIdUseCase(archiveRepository)
        )
    }

    @Singleton
    @Provides
    fun providesUserUseCase(
        userRepository: UserRepository
    ): UserUseCase {
        return UserUseCase(
            getMyInfoUseCase = GetMyInfoUseCase(userRepository),
            getUserByUserIdUseCase = GetUserByUserIdUseCase(userRepository)
        )
    }

    @Singleton
    @Provides
    fun providesStorageUseCase(
        storageRepository: StorageRepository
    ): StorageUseCase {
        return StorageUseCase(
            getStoragesUseCase = GetStoragesUseCase(storageRepository)
        )
    }

    @Singleton
    @Provides
    fun provideMapUseCase(
        mapRepository: MapRepository,
        searchRegionRepository: SearchRegionRepository
    ): MapUseCases {
        return MapUseCases(
            getAddressUseCase = GetAddressUseCase(mapRepository),
            getSearchRegionListUseCase = GetSearchRegionListUseCase(searchRegionRepository),
            getSearchRegionHistory = GetSearchRegionHistoryUseCase(searchRegionRepository),
            clearSearchRegionHistory = ClearSearchRegionHistoryUseCase(searchRegionRepository),
            saveSearchRegionHistory = SaveSearchRegionHistoryUseCase(searchRegionRepository)
        )
    }
}