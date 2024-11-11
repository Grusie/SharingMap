package com.grusie.sharingmap.ui.main.mypage

import com.grusie.sharingmap.ui.model.ArchiveUiModel
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

sealed interface MyPageUiState {
    data object Loading: MyPageUiState
    data class Success(
        val user: UserUiModel = UserUiModel(),
        val feeds: List<ArchiveUiModel> = emptyList(),
        val storages: List<StorageUiModel> = emptyList()
    ): MyPageUiState
    data class Error(val message: String): MyPageUiState
}
