package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArchivesDto(
    @SerialName("meta") val meta: CountDto,
    @SerialName("archives") val archives: List<ArchiveDto>,
)
