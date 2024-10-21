package com.grusie.sharingmap.ui.main.home

import com.grusie.sharingmap.ui.model.ArchiveType
import com.grusie.sharingmap.ui.model.ArchiveUiModel

data class HomeUiState(
    val feeds: List<ArchiveUiModel> = emptyList(),
    val hasNotifications: Boolean = false,
    val selectedArchiveType: ArchiveType = ArchiveType.ALL,
    val selectedFeed: ArchiveUiModel? = null,
)
