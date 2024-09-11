package com.grusie.sharingmap.ui.main.mypage.user

import androidx.lifecycle.ViewModel
import com.grusie.sharingmap.ui.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun setSelectedTabIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
    }

    fun updateUser(user: UserUiModel) {
        _uiState.value = _uiState.value.copy(user = user)
    }

}