package com.grusie.sharingmap.ui.model

data class AdditionalArchiveModel(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val address: String = "",
    val placeName: String = "",
    val content: String = "",
    val isPublic: Boolean = false
)