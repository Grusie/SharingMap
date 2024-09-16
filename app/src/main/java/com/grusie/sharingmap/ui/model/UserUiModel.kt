package com.grusie.sharingmap.ui.model


data class UserUiModel(
    val id: Long = 0L,
    val profileImage: String = "",
    val name: String = "",
    val description: String = "",
    val email: String = "",
    val followerCount: Int = 0,
    val postCount: Int = 0,
)
