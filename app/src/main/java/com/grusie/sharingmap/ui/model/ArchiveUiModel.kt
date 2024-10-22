package com.grusie.sharingmap.ui.model

data class ArchiveUiModel(
    val id: Long,
    val user: UserUiModel,
    val date: String,
    val content: String,
    val archivings: List<UserUiModel> = emptyList(),
    val archiveAttaches: List<ArchiveAttachUiModel> = emptyList(),
    val location: LocationUiModel,
    val feedInfo: ArchiveInfoUiModel,
    val comments: List<CommentUiModel> = emptyList(),
)
