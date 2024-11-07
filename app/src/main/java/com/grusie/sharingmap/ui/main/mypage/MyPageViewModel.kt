package com.grusie.sharingmap.ui.main.mypage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.data.remote.RemoteError
import com.gruise.domain.usecase.archive.ArchiveUseCase
import com.gruise.domain.usecase.storage.StorageUseCase
import com.gruise.domain.usecase.user.UserUseCase
import com.grusie.sharingmap.ui.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val storageUseCase: StorageUseCase,
    private val archiveUseCase: ArchiveUseCase
) : ViewModel() {

    val storageTitleTextField = TextFieldState()
    private val _uiState = MutableStateFlow<MyPageUiState>(MyPageUiState.Loading)
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex.asStateFlow()

    init {
        getMyPageInfo()
    }

    private fun getMyPageInfo() {
        viewModelScope.launch {
            _uiState.value = MyPageUiState.Loading

            val myInfoDeferred = async { userUseCase.getMyInfoUseCase() }
            val storagesDeferred = async { storageUseCase.getStoragesUseCase() }

            val myInfo = myInfoDeferred.await()
            val storages = storagesDeferred.await()

            if (myInfo.isSuccess) {
                val myFeedDeferred =
                    async { archiveUseCase.getArchivesByAuthorIdUseCase(myInfo.getOrNull()!!.userId) }

                val myFeed = myFeedDeferred.await()

                if (myFeed.isSuccess && storages.isSuccess) {
                    _uiState.value = MyPageUiState.Success(
                        user = myInfo.getOrNull()!!.toUiModel(),
                        feeds = myFeed.getOrNull()!!.map { it.toUiModel() },
                        storages = storages.getOrNull()!!.map { it.toUiModel() }
                    )
                } else {
                    _uiState.value = MyPageUiState.Error((myFeed.exceptionOrNull() as RemoteError).toStringForUser())
                }
            } else {
                _uiState.value = MyPageUiState.Error((myInfo.exceptionOrNull() as RemoteError).toStringForUser())
            }
        }
    }

    fun setSelectedTabIndex(index: Int) {
        _selectedTabIndex.value = index
    }

}