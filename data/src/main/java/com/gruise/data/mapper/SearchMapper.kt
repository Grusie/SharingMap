package com.gruise.data.mapper

import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import com.gruise.data.model.UserDto
import com.gruise.domain.model.TagSearch
import com.gruise.domain.model.User

fun LocalUserSearch.toDomain(): User {
    return User(
        userId = userId,
        profileImage = profileImage,
        name = name,
        description = description,
        email = email
    )
}

fun User.toLocalData(): LocalUserSearch {
    return LocalUserSearch(
        userId = userId,
        profileImage = profileImage,
        name = name,
        description = description,
        email = email
    )
}

fun LocalTagSearch.toDomain(): TagSearch {
    return TagSearch(tagId = tagId, tagName = tagName, tagCount = tagCount)
}

fun TagSearch.toLocalData(): LocalTagSearch {
    return LocalTagSearch(tagId = tagId, tagName = tagName, tagCount = tagCount)
}

fun UserDto.toDomain(): User {
    return User(
        userId = userId,
        profileImage = profileImage,
        name = name,
        description = description,
        email = email
    )
}