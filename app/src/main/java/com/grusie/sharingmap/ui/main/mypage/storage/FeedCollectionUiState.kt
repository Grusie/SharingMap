package com.grusie.sharingmap.ui.main.mypage.storage

import com.grusie.sharingmap.ui.model.ArchiveUiModel

sealed interface FeedCollectionUiState {
    data object Loading: FeedCollectionUiState
    data class Success(val feeds: List<ArchiveUiModel>): FeedCollectionUiState
    data class Error(val message: String): FeedCollectionUiState
}