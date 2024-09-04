package com.gruise.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tagSearch")
data class LocalTagSearch(
    @ColumnInfo(name = "tag_id")
    val tagId: Long,
    @ColumnInfo(name = "tag_name")
    val tagName: String,
    @ColumnInfo(name = "tag_count")
    val tagCount: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}