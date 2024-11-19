package com.grusie.sharingmap.ui.main.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

data class SearchUiState @OptIn(ExperimentalFoundationApi::class) constructor(
    val isLoading: Boolean = false,
    val searchTextField: TextFieldState = TextFieldState(),
    val userSearch: List<UserUiModel> = emptyList(),
    val tagSearch: List<TagUiModel> = emptyList(),
    val selectedTabIndex: Int = 0,
    val errorMessage: String = ""
)
