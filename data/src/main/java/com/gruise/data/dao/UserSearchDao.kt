package com.gruise.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gruise.data.model.LocalUserSearch
import kotlinx.coroutines.flow.Flow

@Dao
interface UserSearchDao {
    @Query("SELECT * FROM userSearch ORDER BY createdAt DESC ")
    fun getAll(): Flow<List<LocalUserSearch>>

    @Query("DELETE FROM userSearch")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSearch(userSearch: LocalUserSearch)

}