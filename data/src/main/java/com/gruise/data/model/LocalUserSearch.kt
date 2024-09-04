package com.gruise.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userSearch")
data class LocalUserSearch(
    @ColumnInfo(name = "user_id")
    val userId: Long,
    @ColumnInfo(name = "profile_image")
    val profileImage: String,
    @ColumnInfo(name = "name")
    val name: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
