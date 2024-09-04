package com.grusie.sharingmap.ui.main.search

import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

data class SearchUiState(
    val selectedTabIndex: Int = 0,
    val userSearch: List<UserUiModel> = emptyList(),
    val userSearchHistory: List<UserUiModel> = emptyList(),
    val tagSearch: List<TagUiModel> = emptyList(),
    val tagSearchHistory: List<TagUiModel> = emptyList(),
)
