package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountDto(
    @SerialName("count") val count: Int
)
