package com.grusie.sharingmap.ui.main.search

import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel


data class SearchUiState(
    val isLoading: Boolean = false,
    val userSearch: List<UserUiModel> = emptyList(),
    val tagSearch: List<TagUiModel> = emptyList(),
    val selectedTabIndex: Int = 0,
    val errorMessage: String = ""
)
