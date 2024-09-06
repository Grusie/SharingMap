package com.grusie.sharingmap.ui.main.mypage

import androidx.lifecycle.ViewModel
import com.grusie.sharingmap.data.fakeFeeds
import com.grusie.sharingmap.data.fakeStorage
import com.grusie.sharingmap.designsystem.component.UserInfo
import com.grusie.sharingmap.ui.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    fun setSelectedTabIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
    }

    init {
        _uiState.value = _uiState.value.copy(
            user = UserUiModel(
                id = 1,
                name = "김민수",
                profileImage = "",
                followerCount = 100,
                postCount = 100,
                description = "안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/안녕하세요. 만나서 반갑습니다/",
            ),
            feeds = fakeFeeds,
            storages = fakeStorage,
            )
    }

}