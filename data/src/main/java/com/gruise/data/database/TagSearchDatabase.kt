package com.gruise.data.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.gruise.data.dao.TagSearchDao
import com.gruise.data.model.LocalTagSearch


@Database(
    entities = [LocalTagSearch::class],
    version = 1,
    exportSchema = false
)
abstract class TagSearchDatabase: RoomDatabase() {
    abstract fun getTagSearchDao(): TagSearchDao
}
