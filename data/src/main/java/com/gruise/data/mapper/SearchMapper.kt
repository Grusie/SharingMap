package com.gruise.data.mapper

import com.gruise.data.model.LocalTagSearch
import com.gruise.data.model.LocalUserSearch
import com.gruise.domain.model.TagSearch
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

fun LocalTagSearch.toDomain(): TagSearch {
    return TagSearch(tagId = tagId, tagName = tagName, tagCount = tagCount)
}

fun TagSearch.toLocalData(): LocalTagSearch {
    return LocalTagSearch(tagId = tagId, tagName = tagName, tagCount = tagCount)
}