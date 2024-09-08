package com.grusie.sharingmap.ui.main.mypage.storage

import androidx.lifecycle.ViewModel
import com.grusie.sharingmap.ui.model.FeedUiModel
import com.grusie.sharingmap.ui.model.StorageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(StorageUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedFeed(feed: FeedUiModel) {
        _uiState.value = _uiState.value.copy(selectedFeed = feed)
    }

    fun updateStorage(storage: StorageUiModel) {
        _uiState.value = _uiState.value.copy(storage = storage)
    }

}