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
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        searchTextField.textAsFlow().debounce(500).mapLatest {
            if (uiState.value.selectedTabIndex == 0) getUserSearch() else getTagSearch()
        }.onEach {
            it.onSuccess {
                if(uiState.value.selectedTabIndex == 0) _uiState.value = _uiState.value.copy(userSearch = it.map { it as UserUiModel})
                else _uiState.value = _uiState.value.copy(tagSearch = it.map { it as TagUiModel })
            }.onFailure {

            }
        }.launchIn(viewModelScope)
        getUserSearchHistory()
        getTagSearchHistory()
    }

    fun setSelectedTabIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
    }

    private suspend fun getUserSearch(): Result<List<UserUiModel>> {
        return searchUseCase.getUserSearchUseCase(searchTextField.text.toString(), 10).map { it.map { it.toUiModel() } }
    }

    private fun getUserSearchHistory() {
        viewModelScope.launch {
            searchUseCase.getAllLocalUserSearchUseCase().collect { result ->
                result.onSuccess { userSearchList ->
                    _uiState.value = _uiState.value.copy(
                        userSearchHistory = userSearchList.map { it.toUiModel() }
                    )
                }.onFailure {
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

    private fun getTagSearch(): Result<List<TagUiModel>> {
        return Result.success(fakeTagSearch)
    }

    private fun getTagSearchHistory(){
        viewModelScope.launch {
            searchUseCase.getAllLocalTagSearchUseCase().collect { result ->
                result.onSuccess { tagSearchList ->
                    _uiState.value = _uiState.value.copy(
                        tagSearchHistory = tagSearchList.map { it.toUiModel() }
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