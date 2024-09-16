package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagsDto(
    @SerialName("meta") val count: CountDto,
    @SerialName("tags") val tags: List<TagDto>
)
