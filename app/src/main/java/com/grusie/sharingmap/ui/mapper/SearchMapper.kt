package com.grusie.sharingmap.ui.mapper

import com.gruise.domain.model.Tag
import com.gruise.domain.model.User
import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

fun User.toUiModel() = UserUiModel(
    id = userId,
    profileImage = profileImage,
    name = name,
    description = description,
    email = email,
)

fun UserUiModel.toDomain() = User(
    userId = id,
    profileImage = profileImage,
    name = name,
    description = description,
    email = email,
)

fun Tag.toUiModel() = TagUiModel(
    id = tagId,
    name = tagName,
    count = tagCount,
)

fun TagUiModel.toDomain() = Tag(
    tagId = id,
    tagName = name,
    tagCount = count,
)