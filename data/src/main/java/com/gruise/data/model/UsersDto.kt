package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersDto(
    @SerialName("meta") val count: CountDto,
    @SerialName("users") val users: List<UserDto>
)