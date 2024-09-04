package com.grusie.sharingmap.ui.main.search

import com.grusie.sharingmap.ui.model.UserUiModel

data class SearchUiState(
    val userSearch: List<UserUiModel> = emptyList(),
    val userSearchHistory: List<UserUiModel> = emptyList(),
)
