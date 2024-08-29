package com.grusie.sharingmap.ui.model

data class FeedUiModel(
    val id: Long,
    val user: UserUiModel,
    val date: String,
    val content: String,
    val contentImages: List<String>,
    val locationUiModel: LocationUiModel,
    val feedInfoUiModel: FeedInfoUiModel,
)
