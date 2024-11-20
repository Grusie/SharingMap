package com.gruise.data.mapper

import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import com.gruise.data.model.TagDto
import com.gruise.data.model.UserDto
import com.gruise.domain.model.Tag
import com.gruise.domain.model.User

fun LocalUserSearch.toDomain(): User {
    return User(
        userId = userId,
        profileImage = profileImage,
        name = name,
        description = description,
        email = email,
    )
}

fun User.toLocalData(): LocalUserSearch {
    return LocalUserSearch(
        userId = userId,
        profileImage = profileImage,
        name = name,
        description = description ?: "",
        email = email,
        createdAt = System.currentTimeMillis()
    )
}

fun LocalTagSearch.toDomain(): Tag {
    return Tag(tagId = tagId, tagName = tagName, tagCount = tagCount)
}

fun Tag.toLocalData(): LocalTagSearch {
    return LocalTagSearch(tagId = tagId, tagName = tagName, tagCount = tagCount, createdAt = System.currentTimeMillis())
}