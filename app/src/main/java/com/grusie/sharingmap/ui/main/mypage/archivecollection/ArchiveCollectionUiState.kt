package com.grusie.sharingmap.ui.main.mypage.archivecollection

import com.grusie.sharingmap.ui.model.ArchiveUiModel

sealed interface ArchiveCollectionUiState {
    data object Loading: ArchiveCollectionUiState
    data class Success(val feeds: List<ArchiveUiModel>): ArchiveCollectionUiState
    data class Error(val message: String): ArchiveCollectionUiState
}