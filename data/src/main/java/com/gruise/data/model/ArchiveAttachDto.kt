package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArchiveAttachDto(
    @SerialName("name") val name: String,
    @SerialName("path") val path: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
)
