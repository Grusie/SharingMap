package com.grusie.sharingmap.ui.main.mypage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.usecase.user.UserUseCase
import com.grusie.sharingmap.data.fakeFeeds
import com.grusie.sharingmap.data.fakeStorage
import com.grusie.sharingmap.designsystem.component.UserInfo
import com.grusie.sharingmap.ui.mapper.toUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
): ViewModel() {

    val storageTitleTextField = TextFieldState()
    private val _uiState = MutableStateFlow<MyPageUiState>(MyPageUiState.Loading)
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex.asStateFlow()

    init {
        /*_uiState.value = _uiState.value.copy(
            user = UserUiModel(
                id = 1,
                name = "김민수",
                profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                followerCount = 100,
                postCount = 100,
                description = "안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/",
            ),
            feeds = fakeFeeds,
            storages = fakeStorage,
            )*/
        getMyInfo()
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            userUseCase.getMyInfoUseCase().onSuccess {
                _uiState.value = MyPageUiState.Success(user = it.toUiModel())
            }.onFailure {
                _uiState.value = MyPageUiState.Error(it.message.toString())
            }
        }
    }

    fun setSelectedTabIndex(index: Int) {
        _selectedTabIndex.value = index
    }

}