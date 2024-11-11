package com.grusie.sharingmap.ui.model

sealed class SearchRegionUiState {
    data object Success : SearchRegionUiState()
    data object Loading : SearchRegionUiState()
    data object Error : SearchRegionUiState()
    data object Init : SearchRegionUiState()
}