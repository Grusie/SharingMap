package com.grusie.sharingmap.ui.main.map

import com.grusie.sharingmap.ui.model.FeedType
import com.grusie.sharingmap.ui.model.FeedUiModel

data class MapUiState(
    val feeds: List<FeedUiModel> = emptyList(),
    val selectedFeedType: FeedType = FeedType.ALL,
    val selectedFeed: FeedUiModel? = null,
)