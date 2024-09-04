package com.grusie.sharingmap.ui.main.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.usecase.search.SearchUseCase
import com.grusie.sharingmap.data.fakeUserSearch
import com.grusie.sharingmap.ui.mapper.toDomain
import com.grusie.sharingmap.ui.mapper.toUiModel
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
            getUserSearch()
        }.onEach {
            it.onSuccess {
                _uiState.value = _uiState.value.copy(it)
            }
        }.launchIn(viewModelScope)
        getUserSearchHistory()
    }

    private fun getUserSearch(): Result<List<UserUiModel>> {
        return Result.success(fakeUserSearch)
    }

    private fun getUserSearchHistory() {
        viewModelScope.launch {
            searchUseCase.getAllUserSearchUseCase().collect { result ->
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
            searchUseCase.insertUserSearchUseCase(userSearch.toDomain()).onFailure {
                //TODO 에러 처리
            }
        }
    }

    fun deleteAllUserSearchHistory() {
        viewModelScope.launch {
            searchUseCase.deleteAllUserSearchUseCase().onFailure {
                //TODO 에러 처리
            }
        }
    }
}