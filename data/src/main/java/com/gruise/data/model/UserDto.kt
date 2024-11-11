package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val userId: Long,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String?,
    @SerialName("description") val description: String? = null,
    @SerialName("profileImage") val profileImage: String,
    @SerialName("follow") val follow: Boolean? = null,
)
