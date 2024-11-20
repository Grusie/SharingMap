package com.grusie.sharingmap.ui.main.mypage.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.data.remote.RemoteError
import com.gruise.domain.usecase.user.UserUseCase
import com.grusie.sharingmap.ui.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun updateSelectedTabIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
    }

    fun getUser(userId: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            userUseCase.getUserByUserIdUseCase(userId).onSuccess {
                _uiState.value = _uiState.value.copy(user = it.toUiModel(), isLoading = false)
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = (it.message as RemoteError).toStringForUser())
            }
        }
    }


    fun updateIsFollow() {
        _uiState.value = _uiState.value.copy(isFollow = !_uiState.value.isFollow)
    }

}