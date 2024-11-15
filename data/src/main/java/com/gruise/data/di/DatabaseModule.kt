package com.gruise.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.gruise.data.database.TagSearchDatabase
import com.gruise.data.database.UserSearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideUserSearchDatabase(
        @ApplicationContext context: Context,
    ): UserSearchDatabase =
        Room.databaseBuilder(context, UserSearchDatabase::class.java, "userSearch.db").build()

    @Singleton
    @Provides
    fun provideUserSearchDao(userSearchDatabase: UserSearchDatabase) =
        userSearchDatabase.getUserSearchDao()

    @Singleton
    @Provides
    fun provideTagSearchDatabase(
        @ApplicationContext context: Context,
    ): TagSearchDatabase =
        Room.databaseBuilder(context, TagSearchDatabase::class.java, "tagSearch.db").build()

    @Singleton
    @Provides
    fun provideTagSearchDao(tagSearchDatabase: TagSearchDatabase) =
        tagSearchDatabase.getTagSearchDao()

    @Singleton
    @Provides
    fun provideSearchRegionHistoryDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile("search_region_history")
        }
    )
}