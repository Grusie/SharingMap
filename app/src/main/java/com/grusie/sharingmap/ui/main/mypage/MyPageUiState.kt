package com.grusie.sharingmap.ui.main.mypage

import com.grusie.sharingmap.ui.model.ArchiveUiModel
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

data class MyPageUiState(
    val isLoading: Boolean = false,
    val user: UserUiModel? = null,
    val feeds: List<ArchiveUiModel> = emptyList(),
    val storages: List<StorageUiModel> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isStorageBottomSheetOpen: Boolean = false,
    val isStorageLock: Boolean = false,
)