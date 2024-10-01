package com.grusie.sharingmap.ui.main.map

import androidx.lifecycle.ViewModel
import com.grusie.sharingmap.data.fakeFeeds
import com.grusie.sharingmap.ui.model.FeedUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState

    init {
        getFeeds()
    }

    private fun getFeeds() {
        _uiState.value = _uiState.value.copy(feeds = fakeFeeds)
    }

    fun updateSelectedFeed(feed: FeedUiModel) {
        _uiState.value = _uiState.value.copy(selectedFeed = feed)
    }
}