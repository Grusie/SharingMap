package com.grusie.sharingmap.ui.main.mypage

import com.grusie.sharingmap.ui.model.FeedUiModel
import com.grusie.sharingmap.ui.model.StorageUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

data class MyPageUiState(
    val selectedTabIndex: Int = 0,
    val user: UserUiModel = UserUiModel(),
    val feeds: List<FeedUiModel> = emptyList(),
    val storages: List<StorageUiModel> = emptyList()
)
