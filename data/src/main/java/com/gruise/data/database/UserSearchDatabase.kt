package com.gruise.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gruise.data.dao.UserSearchDao
import com.gruise.data.model.LocalUserSearch

@Database(
    entities = [LocalUserSearch::class],
    version = 2,
    exportSchema = false
)
abstract class UserSearchDatabase: RoomDatabase() {
    abstract fun getUserSearchDao(): UserSearchDao
}