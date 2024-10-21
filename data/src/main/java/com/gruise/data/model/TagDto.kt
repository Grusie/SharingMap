package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    @SerialName("id") val tagId: Long = 0L,
    @SerialName("name") val tagName: String,
    @SerialName("count") val tagCount: Int = 0
)
