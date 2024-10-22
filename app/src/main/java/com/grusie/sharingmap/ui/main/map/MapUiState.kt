package com.grusie.sharingmap.ui.main.map

import com.grusie.sharingmap.ui.model.ArchiveType
import com.grusie.sharingmap.ui.model.ArchiveUiModel

data class MapUiState(
    val feeds: List<ArchiveUiModel> = emptyList(),
    val selectedFeedType: ArchiveType = ArchiveType.ALL,
    val selectedFeed: ArchiveUiModel? = null,
)