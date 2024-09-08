package com.grusie.sharingmap.ui.model

data class StorageUiModel(
    val id: Long = 0,
    val title: String = "",
    val count: Long = 0,
    val feeds: List<FeedUiModel> = emptyList(),
)
