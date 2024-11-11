package com.grusie.sharingmap.ui.model

sealed class SearchRegionUiModel {
    data object Success : SearchRegionUiModel()
    data object Loading : SearchRegionUiModel()
    data object Error : SearchRegionUiModel()
    data object Init : SearchRegionUiModel()
}