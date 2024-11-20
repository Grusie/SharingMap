package com.grusie.sharingmap.ui.main.search

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.data.remote.RemoteError
import com.gruise.domain.usecase.search.SearchUseCase
import com.grusie.sharingmap.data.fakeTagSearch
import com.grusie.sharingmap.data.fakeUserSearch
import com.grusie.sharingmap.ui.mapper.toDomain
import com.grusie.sharingmap.ui.mapper.toUiModel
import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        _uiState.value.searchTextField.textAsFlow().debounce(300).onEach {
            getSearch()
        }.launchIn(viewModelScope)
        getUserSearchHistory()
        getTagSearchHistory()
    }

    fun setSelectedTabIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
        viewModelScope.launch { getSearch() }
    }

    private suspend fun getSearch() {
        when {
            _uiState.value.searchTextField.text.isEmpty() || _uiState.value.searchTextField.text.isBlank() -> {
                if (_uiState.value.selectedTabIndex == 0) {
                    getUserSearchHistory()
                }
                else {
                    getTagSearchHistory()
                }
            }
                _uiState.value.selectedTabIndex == 0 -> getUserSearch()
            else -> getTagSearch()
        }
    }


    private suspend fun getUserSearch() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")
            searchUseCase.getUserSearchUseCase(_uiState.value.searchTextField.text.toString(), 10).onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, userSearch = it.map { it.toUiModel() })
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = (it as RemoteError).toStringForUser())
            }
        }
    }

    private fun getUserSearchHistory() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")
            searchUseCase.getAllLocalUserSearchUseCase().collect { result ->
                result.onSuccess { userSearchList ->
                    _uiState.value = _uiState.value.copy(isLoading = false, userSearch = userSearchList.map { it.toUiModel() })
                }.onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = "유저 검색 기록을 불러오는데 실패했습니다!")
                }
            }
        }
    }

    fun insertUserSearchHistory(userSearch: UserUiModel) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(errorMessage = "")
            searchUseCase.insertLocalUserSearchUseCase(userSearch.toDomain()).onFailure {
                _uiState.value = _uiState.value.copy(errorMessage = "유저 검색 기록을 추가하는데 실패했습니다!")
            }
        }
    }

    fun deleteAllUserSearchHistory() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(errorMessage = "")
            searchUseCase.deleteAllLocalUserSearchUseCase().onFailure {
                _uiState.value = _uiState.value.copy(errorMessage = "유저 검색 기록을 삭제하는데 실패했습니다!")
            }
        }
    }


    private fun getTagSearch() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")
            searchUseCase.getTagSearchUseCase(_uiState.value.searchTextField.text.toString(), 10).onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, tagSearch = it.map { it.toUiModel() })
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    private fun getTagSearchHistory() {
        viewModelScope.launch {
            searchUseCase.getAllLocalTagSearchUseCase().collect { result ->
                result.onSuccess { tagSearchList ->
                    _uiState.value = _uiState.value.copy(isLoading = false, tagSearch = tagSearchList.map { it.toUiModel() })
                }.onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = "태그 검색 기록을 불러오는데 실패했습니다!")
                }
            }
        }
    }

    fun insertTagSearchHistory(tagSearch: TagUiModel) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(errorMessage = "")
            searchUseCase.insertLocalTagSearchUseCase(tagSearch.toDomain()).onFailure {
                _uiState.value = _uiState.value.copy(errorMessage = "태그 검색 기록을 추가하는데 실패했습니다!")
            }
        }
    }

    fun deleteAllTagSearchHistory() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(errorMessage = "")
            searchUseCase.deleteAllLocalTagSearchUseCase().onFailure {
                _uiState.value = _uiState.value.copy(errorMessage = "태그 검색 기록을 삭제하는데 실패했습니다!")
            }
        }
    }
}