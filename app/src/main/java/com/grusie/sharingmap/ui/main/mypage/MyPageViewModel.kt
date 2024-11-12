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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()
    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    init {
        getMyPageInfo()
    }

    private fun getMyPageInfo() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val myInfoDeferred = async { userUseCase.getMyInfoUseCase() }
            val storagesDeferred = async { storageUseCase.getStoragesUseCase() }

            val myInfo = myInfoDeferred.await()
            val storages = storagesDeferred.await()

            if (myInfo.isSuccess) {
                _uiState.value = _uiState.value.copy(user = myInfo.getOrNull()!!.toUiModel())

                val myFeedDeferred =
                    async { archiveUseCase.getArchivesByAuthorIdUseCase(_uiState.value.user!!.id) }

                val myFeed = myFeedDeferred.await()
                if (myFeed.isSuccess && storages.isSuccess) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        feeds = myFeed.getOrNull()!!.map { it.toUiModel() },
                        storages = storages.getOrNull()!!.map { it.toUiModel() },
                    )
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    _errorMessage.emit((myFeed.exceptionOrNull() as RemoteError).toStringForUser())
                }
            } else {
                _uiState.value = _uiState.value.copy(isLoading = false)
                _errorMessage.emit((myInfo.exceptionOrNull() as RemoteError).toStringForUser())
            }
        }
    }

    fun setSelectedTabIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
    }

    fun updateIsStorageBottomSheetOpen(isOpen: Boolean) {
        _uiState.value = _uiState.value.copy(isStorageBottomSheetOpen = isOpen)
    }

    fun updateIsStorageLock() {
        _uiState.value = _uiState.value.copy(isStorageLock = _uiState.value.isStorageLock.not())
    }
}