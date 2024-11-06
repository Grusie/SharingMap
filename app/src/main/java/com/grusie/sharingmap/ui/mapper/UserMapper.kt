package com.grusie.sharingmap.ui.mapper

import com.gruise.domain.model.User
import com.grusie.sharingmap.ui.model.UserUiModel

fun User.toUiModel() = UserUiModel(
    id = userId,
    profileImage = profileImage,
    name = name,
    description = description,
    email = email,
    follow = follow,
)

fun UserUiModel.toDomain() = User(
    userId = id,
    profileImage = profileImage,
    name = name,
    description = description,
    email = email,
    follow = follow,
)