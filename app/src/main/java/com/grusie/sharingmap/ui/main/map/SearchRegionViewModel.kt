package com.grusie.sharingmap.ui.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.model.SearchRegion
import com.gruise.domain.usecase.map.MapUseCases
import com.grusie.sharingmap.ui.model.SearchRegionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchRegionViewModel @Inject constructor(private val mapUseCases: MapUseCases) :
    ViewModel() {
    private val _uiState: MutableStateFlow<SearchRegionUiModel> =
        MutableStateFlow(SearchRegionUiModel.Init)
    private val _searchRegionList: MutableStateFlow<List<SearchRegion>> =
        MutableStateFlow(emptyList())

    val searchRegionList: StateFlow<List<SearchRegion>> = _searchRegionList
    val uiState: StateFlow<SearchRegionUiModel> = _uiState

    fun getSearchRegionList(keyword: String) {
        viewModelScope.launch {
            _uiState.emit(SearchRegionUiModel.Loading)
            mapUseCases.getSearchRegionListUseCase(keyword).onSuccess {
                _uiState.emit(SearchRegionUiModel.Success)
                _searchRegionList.emit(it)
            }.onFailure {
                _uiState.emit(SearchRegionUiModel.Error)
            }
        }
    }
}