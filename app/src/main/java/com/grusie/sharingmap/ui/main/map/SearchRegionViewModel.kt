package com.grusie.sharingmap.ui.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.usecase.map.MapUseCases
import com.grusie.sharingmap.ui.mapper.toUiModel
import com.grusie.sharingmap.ui.model.SearchRegionUiModel
import com.grusie.sharingmap.ui.model.SearchRegionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchRegionViewModel @Inject constructor(private val mapUseCases: MapUseCases) :
    ViewModel() {
    private val _uiState: MutableStateFlow<SearchRegionUiState> =
        MutableStateFlow(SearchRegionUiState.Init)
    private val _searchRegionList: MutableStateFlow<List<SearchRegionUiModel>> =
        MutableStateFlow(emptyList())

    val searchRegionList: StateFlow<List<SearchRegionUiModel>> = _searchRegionList
    val uiState: StateFlow<SearchRegionUiState> = _uiState

    fun getSearchRegionList(keyword: String) {
        viewModelScope.launch {
            _uiState.emit(SearchRegionUiState.Loading)
            mapUseCases.getSearchRegionListUseCase(keyword).onSuccess { list ->
                _uiState.emit(SearchRegionUiState.Success)
                _searchRegionList.emit(list.map { it.toUiModel() })
            }.onFailure {
                _uiState.emit(SearchRegionUiState.Error)
            }
        }
    }
}