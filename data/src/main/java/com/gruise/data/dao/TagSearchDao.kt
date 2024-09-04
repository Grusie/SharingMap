package com.gruise.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruise.data.model.LocalTagSearch
import kotlinx.coroutines.flow.Flow

@Dao
interface TagSearchDao {
    @Query("SELECT * FROM tagSearch ORDER BY ID DESC ")
    fun getAll(): Flow<List<LocalTagSearch>>

    @Query("DELETE FROM tagSearch")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTagSearch(tagSearch: LocalTagSearch)

}