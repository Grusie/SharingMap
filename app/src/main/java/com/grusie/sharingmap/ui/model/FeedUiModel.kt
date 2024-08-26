package com.grusie.sharingmap.ui.model

data class FeedUiModel(
    val profileImage: String,
    val title: String,
    val date: String,
    val content: String,
    val contentImages: List<String>,
    val locationUiModel: LocationUiModel,
    val feedInfoUiModel: FeedInfoUiModel,
)
