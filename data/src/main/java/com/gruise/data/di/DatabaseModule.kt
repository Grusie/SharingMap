package com.gruise.data.di

import android.content.Context
import androidx.room.Room
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

}