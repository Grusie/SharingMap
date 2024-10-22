package com.grusie.sharingmap.ui.main.search

import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel


sealed interface SearchUiState {
    data object Loading : SearchUiState
    data class SearchSuccess(
        val userSearch: List<UserUiModel> = emptyList(),
        val tagSearch: List<TagUiModel> = emptyList(),
    ) : SearchUiState

    data class Error(val message: String) : SearchUiState
}
