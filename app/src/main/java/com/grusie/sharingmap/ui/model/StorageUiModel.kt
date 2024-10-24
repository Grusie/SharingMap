package com.grusie.sharingmap.ui.model

data class StorageUiModel(
    val id: Long = 0,
    val title: String = "",
    val count: Int = 0,
    val feeds: List<ArchiveUiModel> = emptyList(),
)
