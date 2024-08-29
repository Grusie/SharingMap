package com.grusie.sharingmap.ui.model

data class FeedUiModel(
    val id: Long,
    val user: UserUiModel,
    val date: String,
    val content: String,
    val archivings: List<UserUiModel> = emptyList(),
    val contentImages: List<String>,
    val location: LocationUiModel,
    val feedInfo: FeedInfoUiModel,
    val comments: List<CommentUiModel> = emptyList(),
)
