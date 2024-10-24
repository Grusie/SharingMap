package com.gruise.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoragesDto(
    @SerialName("folders") val storages: List<StorageDto>
)
