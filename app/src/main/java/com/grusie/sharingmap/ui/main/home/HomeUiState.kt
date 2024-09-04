package com.grusie.sharingmap.ui.main.home

import com.grusie.sharingmap.ui.model.FeedType
import com.grusie.sharingmap.ui.model.FeedUiModel

data class HomeUiState(
    val feeds: List<FeedUiModel> = emptyList(),
    val hasNotifications: Boolean = false,
    val selectedFeedType: FeedType = FeedType.ALL,
    val selectedFeed: FeedUiModel? = null,
)
