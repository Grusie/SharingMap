package com.gruise.data.mapper

import com.gruise.data.model.LocalUserSearch
import com.gruise.domain.model.UserSearch

fun LocalUserSearch.toDomain(): UserSearch {
    return UserSearch(
        userId = userId,
        profileImage = profileImage,
        name = name,
    )
}

fun UserSearch.toLocalData(): LocalUserSearch {
    return LocalUserSearch(
        userId = userId,
        profileImage = profileImage,
        name = name,
    )
}