package com.grusie.sharingmap.ui.main.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.usecase.search.SearchUseCase
import com.grusie.sharingmap.data.fakeTagSearch
import com.grusie.sharingmap.data.fakeUserSearch
import com.grusie.sharingmap.ui.mapper.toDomain
import com.grusie.sharingmap.ui.mapper.toUiModel
import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    val searchTextField = TextFieldState()
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex.asStateFlow()


    init {
        searchTextField.textAsFlow().debounce(500).onEach {
            if(it.isEmpty() || it.isBlank()) {
                if(_selectedTabIndex.value == 0) getUserSearchHistory()
                else getTagSearchHistory()
            }
        }.filter { it.isNotEmpty() }.mapLatest {
            if (_selectedTabIndex.value == 0) getUserSearch() else getTagSearch()
        }.onEach {
            it.onSuccess {
                if(_selectedTabIndex.value == 0) _uiState.value = SearchUiState.SearchSuccess(userSearch = it.map { it as UserUiModel})
                else _uiState.value = SearchUiState.SearchSuccess(tagSearch = it.map { it as TagUiModel })
            }.onFailure {
                _uiState.value = SearchUiState.Error(it.message!!)
            }
        }.launchIn(viewModelScope)
        getUserSearchHistory()
        getTagSearchHistory()
    }

    fun setSelectedTabIndex(index: Int) {
        _selectedTabIndex.value = index
    }

    private suspend fun getUserSearch(): Result<List<UserUiModel>> {
        return searchUseCase.getUserSearchUseCase(searchTextField.text.toString(), 10).map { it.map { it.toUiModel() } }
    }

    private fun getUserSearchHistory() {
        viewModelScope.launch {
            searchUseCase.getAllLocalUserSearchUseCase().collect { result ->
                result.onSuccess { userSearchList ->
                    _uiState.value = SearchUiState.SearchSuccess(
                        userSearch = userSearchList.map { it.toUiModel() }
                    )
                }.onFailure {
                    _uiState.value = SearchUiState.Error(it.message!!)
                }
            }
        }
    }

    fun insertUserSearchHistory(userSearch: UserUiModel) {
        viewModelScope.launch {
            searchUseCase.insertLocalUserSearchUseCase(userSearch.toDomain()).onFailure {
                //TODO 에러 처리
            }
        }
    }

    fun deleteAllUserSearchHistory() {
        viewModelScope.launch {
            searchUseCase.deleteAllLocalUserSearchUseCase().onFailure {
                //TODO 에러 처리
            }
        }
    }

    private suspend fun getTagSearch(): Result<List<TagUiModel>> {
        return searchUseCase.getTagSearchUseCase(searchTextField.text.toString(), 10).map { it.map { it.toUiModel() } }
    }

    private fun getTagSearchHistory(){
        viewModelScope.launch {
            searchUseCase.getAllLocalTagSearchUseCase().collect { result ->
                result.onSuccess { tagSearchList ->
                    _uiState.value = SearchUiState.SearchSuccess(
                        tagSearch = tagSearchList.map { it.toUiModel() }
                    )
                }.onFailure {
                }
            }
        }
    }

    fun insertTagSearchHistory(tagSearch: TagUiModel) {
        viewModelScope.launch {
            searchUseCase.insertLocalTagSearchUseCase(tagSearch.toDomain()).onFailure {
                //TODO 에러 처리
            }
        }
    }

    fun deleteAllTagSearchHistory() {
        viewModelScope.launch {
            searchUseCase.deleteAllLocalTagSearchUseCase().onFailure {
                //TODO 에러 처리
            }
        }
    }
}