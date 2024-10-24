package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StorageDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("count") val count: Int,
)
