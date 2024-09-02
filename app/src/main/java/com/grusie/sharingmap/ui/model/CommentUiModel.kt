package com.grusie.sharingmap.ui.model

import java.time.LocalDate

data class CommentUiModel(
    val id: Long,
    val user: UserUiModel,
    val date: LocalDate,
    val content: String,
)
