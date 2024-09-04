package com.grusie.sharingmap.ui.mapper

import com.gruise.domain.model.TagSearch
import com.gruise.domain.model.UserSearch
import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

fun UserSearch.toUiModel() = UserUiModel(
    id = userId,
    profileImage = profileImage,
    name = name,
)

fun UserUiModel.toDomain() = UserSearch(
    userId = id,
    profileImage = profileImage,
    name = name,
)

fun TagSearch.toUiModel() = TagUiModel(
    id = tagId,
    name = tagName,
    count = tagCount,
)

fun TagUiModel.toDomain() = TagSearch(
    tagId = id,
    tagName = name,
    tagCount = count,
)