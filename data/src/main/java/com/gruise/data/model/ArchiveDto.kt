package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArchiveDto(
    @SerialName("id") val id: Long,
    @SerialName("author") val author: UserDto,
    @SerialName("positionX") val positionX: Double,
    @SerialName("positionY") val positionY: Double,
    @SerialName("address") val address: String,
    @SerialName("name") val name: String,
    @SerialName("content") val content: String,
    @SerialName("isPublic") val isPublic: Boolean,
    @SerialName("archiveAttaches") val archiveAttaches: List<ArchiveAttachDto>,
    @SerialName("tags") val tags: List<TagDto>,
)
