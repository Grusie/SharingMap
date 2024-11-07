package com.gruise.data.mapper

import com.gruise.data.model.UserDto
import com.gruise.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        userId = userId,
        name = name,
        email = email,
        description = description,
        profileImage = profileImage,
        follow = follow
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        userId = userId,
        name = name,
        email = email,
        description = description,
        profileImage = profileImage,
        follow = follow
    )
}
