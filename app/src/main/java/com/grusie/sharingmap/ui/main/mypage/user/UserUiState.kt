package com.grusie.sharingmap.ui.main.mypage.user

import com.grusie.sharingmap.ui.model.FeedUiModel
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

data class UserUiState(
    val user: UserUiModel = UserUiModel(),
    val selectedTabIndex: Int = 0,
    val isFollow: Boolean = false,
    val feeds: List<FeedUiModel> = emptyList(),
    val storages: List<StorageUiModel> = emptyList()
)
