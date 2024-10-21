package com.grusie.sharingmap.ui.main.mypage.storage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.usecase.archive.ArchiveUseCase
import com.grusie.sharingmap.ui.mapper.toUiModel
import com.grusie.sharingmap.ui.model.ArchiveUiModel
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.TagUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedCollectionViewModel @Inject constructor(
    private val archiveUseCase: ArchiveUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<FeedCollectionUiState>(FeedCollectionUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _selectedFeed = MutableStateFlow<ArchiveUiModel?>(null)
    val selectedFeed = _selectedFeed.asStateFlow()

    fun updateSelectedFeed(feed: ArchiveUiModel) {
        _selectedFeed.value = feed
    }

    fun updateFeedCollection(storage: StorageUiModel) {

    }

    fun updateFeedCollection(tag: TagUiModel) {
        viewModelScope.launch {
            archiveUseCase.getArchivesUseCase(tag = tag.name).onSuccess {
                Log.d("TAG", "updateFeedCollection: $it")
                _uiState.value = FeedCollectionUiState.Success(feeds = it.map { it.toUiModel() })
            }.onFailure {
                _uiState.value = FeedCollectionUiState.Error(it.message ?: "")
            }
        }
    }

}