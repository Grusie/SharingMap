package com.grusie.sharingmap.ui.main.home

import androidx.lifecycle.ViewModel
import com.grusie.sharingmap.data.fakeFeeds
import com.grusie.sharingmap.ui.model.ArchiveType
import com.grusie.sharingmap.ui.model.ArchiveUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor() : ViewModel() {
        private val _uiState = MutableStateFlow(HomeUiState())
        val uiState: StateFlow<HomeUiState> = _uiState

        init {
            getFeeds()
        }

        private fun getFeeds() {
            if (_uiState.value.selectedArchiveType == ArchiveType.ALL) {
                _uiState.value = _uiState.value.copy(feeds = fakeFeeds)
            } else {
                _uiState.value = _uiState.value.copy(feeds = emptyList())
            }
        }

        fun updateSelectedFeedType(archiveType: ArchiveType) {
            _uiState.value = _uiState.value.copy(selectedArchiveType = archiveType)
            getFeeds()
        }

        fun updateSelectedFeed(feed: ArchiveUiModel) {
            _uiState.value = _uiState.value.copy(selectedFeed = feed)
        }
    }
