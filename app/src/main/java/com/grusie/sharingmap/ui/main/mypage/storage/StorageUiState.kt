package com.grusie.sharingmap.ui.main.mypage.storage

import com.grusie.sharingmap.ui.model.FeedUiModel
import com.grusie.sharingmap.ui.model.StorageUiModel

data class StorageUiState(
    val storage: StorageUiModel = StorageUiModel(),
    val selectedFeed: FeedUiModel? = null
)